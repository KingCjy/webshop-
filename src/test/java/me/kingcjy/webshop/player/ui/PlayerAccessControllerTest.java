package me.kingcjy.webshop.player.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kingcjy.webshop.player.application.PlayerAccessService;
import me.kingcjy.webshop.player.domain.PlayerDto;
import me.kingcjy.webshop.server.domain.ServerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author KingCjy
 */
@WebMvcTest(PlayerAccessController.class)
class PlayerAccessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerAccessService playerAccessService;

    @MockBean
    private ServerRepository serverRepository;

    @Test
    public void joinPlayerTest() throws Exception {
        String secretKey = "secret";

        when(serverRepository.findServerIdBySecretKey(secretKey)).thenReturn(1L);

        PlayerDto.PlayerAccess playerAccess = new PlayerDto.PlayerAccess("uuid", "hi", 0L);
        String content = objectMapper.writeValueAsString(playerAccess);

        mockMvc.perform(post("/api/v1/players/join")
                .header("X-Server-Key", secretKey)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void exitPlayerTest() throws Exception {
        String secretKey = "secret";

        when(serverRepository.findServerIdBySecretKey(secretKey)).thenReturn(1L);

        PlayerDto.PlayerAccess playerAccess = new PlayerDto.PlayerAccess("uuid", "hi", 0L);
        String content = objectMapper.writeValueAsString(playerAccess);

        mockMvc.perform(post("/api/v1/players/exit")
                .header("X-Server-Key", secretKey)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk());
    }

}