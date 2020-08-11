package me.kingcjy.webshop.user.application;

import me.kingcjy.webshop.user.domain.MojangUser;
import me.kingcjy.webshop.user.domain.User;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author KingCjy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserDetailServiceTest {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(
                "KingCjy@gmail.com",
                "1234",
                "KingCjy",
                new MojangUser("KingCjy@gmail.com"));

        userRepository.save(user);
    }

    @Test
    public void getUserDetailTest() {
        UserDto.UserDetail userDetail = userDetailService.getDetail(user.getId());

        assertThat(userDetail).isNotNull();

        assertThat(userDetail).isEqualTo(new UserDto.UserDetail(user.getEmail(), user.getUsername()));
    }

    @Test
    public void changeUsernameTest() {
        String username = "dodo123";
        UserDto.UsernameChange usernameChange = new UserDto.UsernameChange(username, user.getId());

        userDetailService.changeUsername(usernameChange);
        User actualUser = userRepository.findById(user.getId()).orElse(null);

        assertThat(actualUser.getUsername()).isEqualTo(username);
    }
}