package me.kingcjy.webshop.user.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.user.application.UserLoginService;
import me.kingcjy.webshop.user.domain.UserDto;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDto.UserLogin userLogin) {
        UserDto.UserToken userToken = userLoginService.login(userLogin);
        return Response.success(userToken);
    }
}
