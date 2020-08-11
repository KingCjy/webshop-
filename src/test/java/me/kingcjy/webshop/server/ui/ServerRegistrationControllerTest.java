package me.kingcjy.webshop.server.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.kingcjy.webshop.config.ControllerTest;
import me.kingcjy.webshop.server.application.ServerDto;
import me.kingcjy.webshop.server.application.ServerRegistrationService;
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
@WebMvcTest(ServerRegistrationController.class)
class ServerRegistrationControllerTest extends ControllerTest {

    @MockBean
    private ServerRegistrationService serverRegistrationService;

    @BeforeEach
    public void setUp() {
        when(serverRegistrationService.registration(any())).thenReturn(1L);
    }

    @Test
    public void serverRegistrationTest() throws Exception {
        ServerDto.ServerRegistration serverRegistration = new ServerDto.ServerRegistration("최재용서버", "재밌다", "이미지", "12.20.1", "kingcjy.oa.to", 25565, null);
        String content = objectMapper.writeValueAsString(serverRegistration);

        System.out.println(content);
        mockMvc.perform(post("/api/v1/servers")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isCreated());
    }
}