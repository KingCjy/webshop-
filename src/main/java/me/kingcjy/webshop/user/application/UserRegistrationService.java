package me.kingcjy.webshop.user.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.api.mojang.AuthenticateResponse;
import me.kingcjy.webshop.api.mojang.MojangApiService;
import me.kingcjy.webshop.user.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final MojangApiService mojangApiService;

    @Transactional
    public Long registration(UserDto.UserRegistration userRegistration) {
        if(isEmailAlreadyUsed(userRegistration.getEmail())) {
            throw new EmailAlreadyUsedException(userRegistration.getEmail(), "이미 사용중인 메일입니다.");
        }

        AuthenticateResponse response = mojangApiService.authenticate(userRegistration.getEmail(), userRegistration.getPassword());
        String uuid = response.getSelectedProfile().getUuid();

        User user;

        if(isWaitUser(uuid)) {
            user = userRepository.findByUUID(uuid);
            user.registration(userRegistration.getEmail(), userRegistration.getPassword(), userRegistration.getUsername());
        } else {
            user = new User(userRegistration.getEmail(),
                    userRegistration.getPassword(),
                    userRegistration.getUsername(),
                    new MojangUser(uuid));

            userRepository.save(user);
        }

        return user.getId();
    }

    private boolean isEmailAlreadyUsed(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    private boolean isWaitUser(String uuid) {
        User user = userRepository.findByUUID(uuid);
        return user != null && UserStatus.WAIT_REGISTRATION.equals(user.getStatus());
    }
}
