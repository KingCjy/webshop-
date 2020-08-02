package me.kingcjy.webshop.server.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.security.SecurityUser;
import me.kingcjy.webshop.server.application.ServerDetailService;
import me.kingcjy.webshop.server.application.ServerDto;
import me.kingcjy.webshop.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author KingCjy
 */
@RestController
@RequestMapping("/api/v1/servers")
@RequiredArgsConstructor
public class ServerDetailController {

    private final ServerDetailService serverDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getServerDetail(
            SecurityUser securityUser,
            @PathVariable(name = "id") Long serverId) {
        ServerDto.ServerDetailResponse serverDetail = serverDetailService.getServerDetail(serverId, securityUser.getUserId());
        return Response.success(serverDetail);
    }
}
