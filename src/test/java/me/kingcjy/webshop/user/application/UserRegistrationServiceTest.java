package me.kingcjy.webshop.user.application;

import me.kingcjy.webshop.user.domain.User;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author KingCjy
 */
@SpringBootTest
class UserRegistrationServiceTest {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void registrationUserTest() {
        String email = "KingCjy@gmail.com";
        String password = "asd123";
        String nickName = "KingCjy";

        UserDto.UserRegistration userRegistration = new UserDto.UserRegistration(email, password, nickName);
        userRegistrationService.registration(userRegistration);

        User user = userRepository.findByEmail(email);

        assertThat(user).isNotNull();
        assertThat(user.getPassword().match(password)).isTrue();
        assertThat(user.getUsername()).isEqualTo(nickName);
    }
}