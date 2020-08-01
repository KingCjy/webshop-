package me.kingcjy.webshop.user.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.model.ReturnId;
import me.kingcjy.webshop.user.application.UserRegistrationService;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.Validator;

/**
 * @author KingCjy
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping
    public ResponseEntity<?> registration(@RequestBody @Valid UserDto.UserRegistration userRegistration) {
        Long id = userRegistrationService.registration(userRegistration);
        String location = "/api/v1/users/" + id;
        return Response.created(ReturnId.from(id), location);
    }
}
