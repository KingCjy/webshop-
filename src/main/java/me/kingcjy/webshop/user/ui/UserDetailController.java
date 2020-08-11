package me.kingcjy.webshop.user.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.exception.PermissionDeniedException;
import me.kingcjy.webshop.common.security.SecurityUser;
import me.kingcjy.webshop.user.application.UserDetailService;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author KingCjy
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserDetailController {

    private final UserDetailService userDetailService;

    @GetMapping("/detail")
    public ResponseEntity<?> getUserDetail(SecurityUser securityUser) {
        UserDto.UserDetail userDetail = userDetailService.getDetail(securityUser.getUserId());
        return Response.success(userDetail);
    }

    @PatchMapping("/username")
    public ResponseEntity<?> changeUsername(SecurityUser securityUser,
                                            @RequestBody @Valid UserDto.UsernameChange usernameChange) {
        userDetailService.changeUsername(usernameChange);
        return Response.success();
    }
}
