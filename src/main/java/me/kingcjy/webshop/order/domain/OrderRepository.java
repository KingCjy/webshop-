package me.kingcjy.webshop.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author KingCjy
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
