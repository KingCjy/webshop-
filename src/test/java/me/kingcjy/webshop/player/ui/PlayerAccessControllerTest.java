package me.kingcjy.webshop.player.ui;

import me.kingcjy.webshop.config.ControllerTest;
import me.kingcjy.webshop.player.application.PlayerAccessService;
import me.kingcjy.webshop.player.application.PlayerDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author KingCjy
 */
@WebMvcTest(PlayerAccessController.class)
class PlayerAccessControllerTest extends ControllerTest {

    @MockBean
    private PlayerAccessService playerAccessService;

    @Test
    public void joinPlayerTest() throws Exception {
        PlayerDto.PlayerAccess playerAccess = new PlayerDto.PlayerAccess("uuid", "hi", 0L);
        String content = objectMapper.writeValueAsString(playerAccess);

        System.out.println(content);
        mockMvc.perform(post("/plugin/api/v1/players/join")
                .header("X-Server-Key", secretKey)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void exitPlayerTest() throws Exception {
        PlayerDto.PlayerAccess playerAccess = new PlayerDto.PlayerAccess("uuid", "hi", 0L);
        String content = objectMapper.writeValueAsString(playerAccess);

        mockMvc.perform(post("/plugin/api/v1/players/exit")
                .header("X-Server-Key", secretKey)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk());
    }

}