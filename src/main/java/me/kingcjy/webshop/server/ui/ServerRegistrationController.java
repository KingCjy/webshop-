package me.kingcjy.webshop.server.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.model.ReturnId;
import me.kingcjy.webshop.common.security.SecurityUser;
import me.kingcjy.webshop.server.application.ServerDto;
import me.kingcjy.webshop.server.application.ServerRegistrationService;
import me.kingcjy.webshop.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author KingCjy
 */
@RestController
@RequestMapping("/api/v1/servers")
@RequiredArgsConstructor
public class ServerRegistrationController {

    private final ServerRegistrationService serverRegistrationService;

    @PostMapping
    public ResponseEntity<ReturnId> registration(
            SecurityUser securityUser,
            @RequestBody @Valid ServerDto.ServerRegistration serverRegistration) {
        serverRegistration.setOwnerId(securityUser.getUserId());
        Long id = serverRegistrationService.registration(serverRegistration);
        String location = "/api/v1/servers/" + id;
        return Response.created(ReturnId.from(id), location);
    }
}
