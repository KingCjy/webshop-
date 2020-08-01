package me.kingcjy.webshop.user.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.kingcjy.webshop.config.ControllerTest;
import me.kingcjy.webshop.user.application.UserLoginService;
import me.kingcjy.webshop.user.domain.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author KingCjy
 */
@WebMvcTest(UserLoginController.class)
class UserLoginControllerTest extends ControllerTest {

    @MockBean
    private UserLoginService userLoginService;

    @BeforeEach
    public void setUp() {
        UserDto.UserToken userToken = new UserDto.UserToken("token");
        when(userLoginService.login(any())).thenReturn(userToken);
    }

    @Test
    public void userLoginTest() throws Exception {
        UserDto.UserLogin userLogin = new UserDto.UserLogin("as@as.as", "as");
        String content = objectMapper.writeValueAsString(userLogin);

        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
        )
                .andExpect(status().isOk());
    }
}