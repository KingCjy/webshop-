package me.kingcjy.webshop.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author KingCjy
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByServerIdAndStatus(Long serverId, OrderStatus status);
}
