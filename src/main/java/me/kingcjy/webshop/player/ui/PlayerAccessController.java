package me.kingcjy.webshop.player.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.config.annotation.ServerId;
import me.kingcjy.webshop.player.application.PlayerAccessService;
import me.kingcjy.webshop.player.application.PlayerDto;
import me.kingcjy.webshop.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author KingCjy
 */
@RestController
@RequestMapping("/plugin/api/v1/players")
@RequiredArgsConstructor
public class PlayerAccessController {

    private final PlayerAccessService playerAccessService;

    @PostMapping("/join")
    public ResponseEntity<Object> playerJoin(
            @ServerId Long serverId,
            @RequestBody @Valid PlayerDto.PlayerAccess playerAccess) {
        playerAccess.setServerId(serverId);
        playerAccessService.onPlayerJoin(playerAccess);
        return Response.success();
    }

    @PostMapping("/exit")
    public ResponseEntity<Object> playerExit(
            @ServerId Long serverId,
            @RequestBody @Valid PlayerDto.PlayerAccess playerAccess) {
        playerAccess.setServerId(serverId);
        playerAccessService.onPlayerExit(playerAccess);
        return Response.success();
    }
}
