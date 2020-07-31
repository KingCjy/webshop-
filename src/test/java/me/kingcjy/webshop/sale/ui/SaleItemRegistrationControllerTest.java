package me.kingcjy.webshop.sale.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.kingcjy.webshop.common.service.ImageService;
import me.kingcjy.webshop.sale.application.SaleItemDto;
import me.kingcjy.webshop.sale.application.SaleItemRegistrationService;
import me.kingcjy.webshop.server.domain.ServerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author KingCjy
 */
@WebMvcTest(SaleItemRegistrationController.class)
class SaleItemRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SaleItemRegistrationService saleItemRegistrationService;

    private MockMultipartFile file = new MockMultipartFile("image", "image".getBytes());
    private Long id = 1L;

    @MockBean
    private ServerRepository serverRepository;

    private String secretKey = "secret";

    @BeforeEach
    public void setUp() {
        when(serverRepository.findServerIdBySecretKey(secretKey)).thenReturn(1L);
        when(saleItemRegistrationService.registration(any(SaleItemDto.SaleItemRequest.class))).thenReturn(id);
    }

    @Test
    public void registrationSaleItemTest() throws Exception {
        String secretKey = "secret";

        SaleItemDto.SaleItemRequest saleItemRequest = new SaleItemDto.SaleItemRequest();
        String content = objectMapper.writeValueAsString(saleItemRequest);

        mockMvc.perform(post("/api/v1/sale/plugin")

                .content(content).contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk());
//
//        when(serverRepository.findServerIdBySecretKey(secretKey)).thenReturn(1L);
//
//        PlayerDto.PlayerAccess playerAccess = new PlayerDto.PlayerAccess("uuid", "hi", 0L);
//        String content = objectMapper.writeValueAsString(playerAccess);
//
//        mockMvc.perform(post("/api/v1/players/join")
//                .header("X-Server-Key", secretKey)
//                .content(content)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//        )
//                .andExpect(status().isOk());


    }
}