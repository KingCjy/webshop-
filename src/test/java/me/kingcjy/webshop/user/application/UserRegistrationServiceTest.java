package me.kingcjy.webshop.user.application;

import me.kingcjy.webshop.api.mojang.AuthenticateResponse;
import me.kingcjy.webshop.api.mojang.MojangApiService;
import me.kingcjy.webshop.api.mojang.SelectedProfile;
import me.kingcjy.webshop.user.domain.User;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author KingCjy
 */
@SpringBootTest
class UserRegistrationServiceTest {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private MojangApiService mojangApiService;

    @Test
    public void registrationUserTest() {
        String email = "KingCjy@gmail.com";
        String password = "asd123";
        String nickName = "KingCjy";

        SelectedProfile selectedProfile = new SelectedProfile();
        selectedProfile.setUuid("uuid");

        AuthenticateResponse authenticateResponse = new AuthenticateResponse("token", selectedProfile);
        when(mojangApiService.authenticate(email, password)).thenReturn(authenticateResponse);

        UserDto.UserRegistration userRegistration = new UserDto.UserRegistration(email, password, nickName);
        userRegistrationService.registration(userRegistration);

        User user = userRepository.findByEmail(email);

        assertThat(user).isNotNull();
        assertThat(user.getPassword().match(password)).isTrue();
        assertThat(user.getUsername()).isEqualTo(nickName);
    }
}