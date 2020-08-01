package me.kingcjy.webshop.user.application;

import me.kingcjy.webshop.common.service.JwtService;
import me.kingcjy.webshop.user.domain.MojangUser;
import me.kingcjy.webshop.user.domain.User;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author KingCjy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserLoginServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginServiceTest.class);

    @Autowired
    private UserLoginService userLoginService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User user = new User("as@as.as", "as", "KingCjy", new MojangUser("uuid"));
        when(userRepository.findByEmail(any())).thenReturn(user);
    }

    @Test
    public void userLoginTest() {
        UserDto.UserLogin userLogin = new UserDto.UserLogin("as@as.as", "as");
        UserDto.UserToken userToken = userLoginService.login(userLogin);

        logger.info("{}", userToken);
        assertThat(userToken).isNotNull();
    }
}
