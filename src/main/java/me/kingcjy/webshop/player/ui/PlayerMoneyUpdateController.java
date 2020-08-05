package me.kingcjy.webshop.player.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.config.annotation.ServerId;
import me.kingcjy.webshop.player.application.PlayerDto;
import me.kingcjy.webshop.player.application.PlayerMoneyUpdateService;
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
@RequestMapping("/plugin/api/v1/players/money")
@RequiredArgsConstructor
public class PlayerMoneyUpdateController {

    private final PlayerMoneyUpdateService playerMoneyUpdateService;

    @PostMapping
    public ResponseEntity<Object> updateMoney(
            @ServerId Long serverId,
            @RequestBody @Valid PlayerDto.PlayerMoneyUpdate playerMoneyUpdate) {
        playerMoneyUpdate.setServerId(serverId);
        playerMoneyUpdateService.updateMoney(playerMoneyUpdate);

        return Response.success();
    }
}
