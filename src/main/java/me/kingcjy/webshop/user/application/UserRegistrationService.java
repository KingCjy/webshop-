package me.kingcjy.webshop.user.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.user.domain.User;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;

    @Transactional
    public Long registration(UserDto.UserRegistration userRegistration) {
        User user = new User(userRegistration.getEmail(),
                userRegistration.getPassword(),
                userRegistration.getUsername());

        userRepository.save(user);
        return user.getId();
    }
}
