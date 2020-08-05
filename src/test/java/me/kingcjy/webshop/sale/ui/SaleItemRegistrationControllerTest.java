package me.kingcjy.webshop.sale.ui;

import me.kingcjy.webshop.config.ControllerTest;
import me.kingcjy.webshop.sale.application.SaleItemDto;
import me.kingcjy.webshop.sale.application.SaleItemRegistrationService;
import me.kingcjy.webshop.server.domain.ServerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author KingCjy
 */
@WebMvcTest(SaleItemRegistrationController.class)
class SaleItemRegistrationControllerTest extends ControllerTest {

    @MockBean
    private SaleItemRegistrationService saleItemRegistrationService;

    private Long id = 1L;

    @MockBean
    private ServerRepository serverRepository;

    @BeforeEach
    public void setUp() {
        when(saleItemRegistrationService.registrationInPlugin(any(SaleItemDto.SaleItemRequest.class))).thenReturn(id);
    }

    @Test
    public void registrationSaleItemTest() throws Exception {
        SaleItemDto.SaleItemRequest saleItemRequest = new SaleItemDto.SaleItemRequest("uuid", "name", "description", 1, 1, "item", "image", null);
        String content = objectMapper.writeValueAsString(saleItemRequest);

        mockMvc.perform(post("/plugin/api/v1/sale")
                .header(X_SERVER_KEY, secretKey)
                .content(content).contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isCreated());
    }
}