package me.kingcjy.webshop.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kingcjy.webshop.common.model.Money;

import javax.persistence.*;

/**
 * @author KingCjy
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "purchase_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private Long ordererId;
    private Long saleItemId;

    private Money totalAmounts;
    private Money price;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(Long ordererId, Long saleItemId, Money price, Integer quantity) {
        this.ordererId = ordererId;
        this.saleItemId = saleItemId;
        this.price = price;
        this.quantity = quantity;
        this.status = OrderStatus.ORDERED;
        this.totalAmounts = price.multiply(quantity);
    }
}
