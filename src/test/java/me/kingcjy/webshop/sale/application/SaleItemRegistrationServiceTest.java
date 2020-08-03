package me.kingcjy.webshop.sale.application;

import me.kingcjy.webshop.common.model.Money;
import me.kingcjy.webshop.sale.domain.Item;
import me.kingcjy.webshop.sale.domain.SaleItem;
import me.kingcjy.webshop.sale.domain.SaleItemRepository;
import me.kingcjy.webshop.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author KingCjy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SaleItemRegistrationServiceTest {

    @Autowired
    private SaleItemRegistrationService saleItemRegistrationService;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @MockBean
    private UserRepository userRepository;

    private long sellerId = 1L;

    @BeforeEach
    public void setUp() {
        when(userRepository.findUserIdFromUUID("KingCjyUUID")).thenReturn(sellerId);
    }

    @Test
    public void registrationSaleItemTest() {
        String uuid = "KingCjyUUID";
        String name = "집행검";
        String description = "졸라짱짱쎔";
        int quantity = 10;
        int price = 10000;
        String item = "asdjfaskfbaksdlfbakfjadkfba";
        String image = "http://naver.com";

        SaleItemDto.SaleItemRequest saleItemRequest = new SaleItemDto.SaleItemRequest(uuid, name, description, quantity, price, item, image);
        Long saleItemId = saleItemRegistrationService.registrationInPlugin(saleItemRequest);
        SaleItem saleItem = saleItemRepository.findById(saleItemId).orElse(null);

        assertThat(saleItem).isNotNull();
        assertThat(saleItem.getItem()).isEqualTo(new Item(name, description, image, item));
        assertThat(saleItem.getPrice()).isEqualTo(new Money(price));
        assertThat(saleItem.getQuantity()).isEqualTo(quantity);
        assertThat(saleItem.getSellerId()).isEqualTo(sellerId);
    }
}