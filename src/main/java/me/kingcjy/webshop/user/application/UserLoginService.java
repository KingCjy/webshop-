package me.kingcjy.webshop.user.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.service.JwtService;
import me.kingcjy.webshop.user.domain.User;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.user.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public UserDto.UserToken login(UserDto.UserLogin userLogin) {
        User user = userRepository.findByEmail(userLogin.getEmail());

        if(user == null || !user.getPassword().match(userLogin.getPassword())) {
            throw new LoginFailException("이메일 혹은 비밀번호를 확인해주세요");
        }

        return new UserDto.UserToken(jwtService.create(user));
    }
}
