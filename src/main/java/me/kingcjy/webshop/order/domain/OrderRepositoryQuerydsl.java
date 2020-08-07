package me.kingcjy.webshop.order.domain;

import me.kingcjy.webshop.order.application.OrderDto;

import java.util.List;

/**
 * @author KingCjy
 */
public interface OrderRepositoryQuerydsl {
    List<OrderDto.WaitOrder> findWaitOrders(Long serverId);
}
