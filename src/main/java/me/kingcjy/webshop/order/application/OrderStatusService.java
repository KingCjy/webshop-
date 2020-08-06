package me.kingcjy.webshop.order.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.order.domain.Order;
import me.kingcjy.webshop.order.domain.OrderRepository;
import me.kingcjy.webshop.order.domain.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class OrderStatusService {
    private final OrderRepository orderRepository;

    public void findWaitOrders(Long serverId) {
        List<Order> orders = orderRepository.findByServerIdAndStatus(serverId, OrderStatus.WAIT);
    }
}
