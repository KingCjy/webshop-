package me.kingcjy.webshop.order.application;

import me.kingcjy.webshop.common.model.Money;
import me.kingcjy.webshop.order.domain.OrderRepository;
import me.kingcjy.webshop.player.domain.Player;
import me.kingcjy.webshop.player.domain.PlayerRepository;
import me.kingcjy.webshop.sale.application.SaleItemDto;
import me.kingcjy.webshop.sale.domain.Item;
import me.kingcjy.webshop.sale.domain.SaleItem;
import me.kingcjy.webshop.sale.domain.SaleItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author KingCjy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderCreateServiceTest {

    @Autowired
    private OrderCreateService orderCreateService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private SaleItemRepository saleItemRepository;

    @MockBean
    private PlayerRepository playerRepository;

    private SaleItem saleItem;

    @BeforeEach
    public void setUp() {
        SaleItemDto.SaleItemRequest saleItemRequest = new SaleItemDto.SaleItemRequest("uuid", "name", "description", 1, 1, "item", "image", null);

        this.saleItem = new SaleItem(
                saleItemRequest.getServerId(),
                1L,
                new Item(saleItemRequest.getName(), saleItemRequest.getDescription(), saleItemRequest.getImage(), saleItemRequest.getItem()),
                new Money(saleItemRequest.getPrice()),
                saleItemRequest.getQuantity());
        when(saleItemRepository.findById(any())).thenReturn(Optional.of(this.saleItem));

        Player player = new Player(1L, 1L, "uuuid");
        player.updateMoney(new Money(10000));
        when(playerRepository.findByServerIdAndUserId(any(), any())).thenReturn(player);
    }


    @Test
    public void orderCreateTest() {
        OrderDto.OrderRequest orderRequest = new OrderDto.OrderRequest(1L, 2, 1L);

        Long orderId = orderCreateService.createOrder(orderRequest);
    }

}