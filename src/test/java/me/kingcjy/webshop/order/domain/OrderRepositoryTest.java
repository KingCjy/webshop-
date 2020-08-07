package me.kingcjy.webshop.order.domain;

import me.kingcjy.webshop.common.model.Money;
import me.kingcjy.webshop.order.application.OrderDto;
import me.kingcjy.webshop.player.domain.Player;
import me.kingcjy.webshop.player.domain.PlayerRepository;
import me.kingcjy.webshop.sale.domain.Item;
import me.kingcjy.webshop.sale.domain.SaleItem;
import me.kingcjy.webshop.sale.domain.SaleItemRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author KingCjy
 */
@DataJpaTest
class OrderRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryTest.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setUp() {
        Player seller = new Player(1L, 1L, "uuid1");
        Player buyer = new Player(2L, 1L, "uuid2");

        playerRepository.save(seller);
        playerRepository.save(buyer);

        SaleItem saleItem = new SaleItem(1L, 1L, new Item("아이템1", "아이템 설", "이미지", "item"), new Money(1), 1);
        saleItemRepository.save(saleItem);

        Order order = new Order(1L, 1L, 2L, 1L, new Money(10), 1);
        orderRepository.save(order);
    }

    @Test
    public void findWaitOrdersTest() {
        List<OrderDto.WaitOrder> waitOrders = orderRepository.findWaitOrders(1L);

        assertThat(waitOrders).isNotEmpty();
        logger.info("WaitOrders: {}", waitOrders);
    }
}