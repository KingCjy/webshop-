package me.kingcjy.webshop.order.application;

import me.kingcjy.webshop.common.model.Money;
import me.kingcjy.webshop.order.domain.Order;
import me.kingcjy.webshop.order.domain.OrderRepository;
import me.kingcjy.webshop.order.domain.OrderStatus;
import me.kingcjy.webshop.player.domain.Player;
import me.kingcjy.webshop.player.domain.PlayerRepository;
import me.kingcjy.webshop.sale.domain.Item;
import me.kingcjy.webshop.sale.domain.SaleItem;
import me.kingcjy.webshop.sale.domain.SaleItemRepository;
import me.kingcjy.webshop.server.domain.ServerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author KingCjy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderStatusServiceTest {

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private ServerRepository serverRepository;


    @BeforeEach
    public void setUp() {
        Player seller = new Player(1L, 1L, "uuid1");
        Player buyer = new Player(2L, 1L, "uuid2");

        playerRepository.save(seller);
        playerRepository.save(buyer);

        SaleItem saleItem = new SaleItem(1L, 1L, new Item("아이템1", "아이템 설", "이미지", "item"), new Money(1), 10);
        saleItemRepository.save(saleItem);
    }

    @Test
    public void findWaitOrdersTest() {
        Order order = new Order(1L, 1L, 2L, 1L, new Money(10), 1);
        orderRepository.save(order);

        List<OrderDto.WaitOrder> waitOrders = orderStatusService.findWaitOrders(1L);

        assertThat(waitOrders).isNotNull();
        assertThat(waitOrders.isEmpty()).isFalse();

//        Long orderId, String sellerUuid, String buyerUuid, Integer quantity, Money totalAmounts, String item, LocalDateTime orderedAt
        assertThat(waitOrders.get(0)).isEqualTo(new OrderDto.WaitOrder(order.getId(), "uuid1", "uuid2", 1, order.getTotalAmounts(), "item", order.getCreatedAt()));
    }

    @Test
    public void acceptOrderTest() {
        Order order = new Order(1L, 1L, 2L, 1L, new Money(10), 1);
        orderRepository.save(order);

        OrderDto.AcceptOrder acceptOrder = new OrderDto.AcceptOrder(List.of(1L), 1L);
        orderStatusService.acceptOrders(acceptOrder);

        order = orderRepository.findById(order.getId()).orElse(null);
        Player seller = playerRepository.findById(1L).orElse(null);
        Player orderer = playerRepository.findById(2L).orElse(null);

        assertThat(order).isNotNull();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETE);

        assertThat(seller).isNotNull();
        assertThat(seller.getMoney()).isEqualTo(new Money(10));

        assertThat(orderer).isNotNull();
    }

    @Test
    public void rejectOrderTest() {
        Order order = new Order(1L, 1L, 2L, 1L, new Money(10), 1);
        orderRepository.save(order);

        OrderDto.RejectOrder rejectOrder = new OrderDto.RejectOrder(1L, "내맘이야", 1L);
        orderStatusService.rejectOrders(rejectOrder);

        order = orderRepository.findById(order.getId()).orElse(null);
        Player seller = playerRepository.findById(1L).orElse(null);
        Player orderer = playerRepository.findById(2L).orElse(null);

        assertThat(order).isNotNull();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.REJECT  );
        assertThat(order.getCause()).isNotEmpty();

        assertThat(seller).isNotNull();
        assertThat(seller.getMoney()).isEqualTo(new Money(0));

        assertThat(orderer).isNotNull();
        assertThat(orderer.getMoney()).isEqualTo(new Money(10));
    }
}