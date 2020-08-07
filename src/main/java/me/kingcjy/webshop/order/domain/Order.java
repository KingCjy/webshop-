package me.kingcjy.webshop.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kingcjy.webshop.common.model.Money;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private Long serverId;
    private Long sellerId;
    private Long ordererId;
    private Long saleItemId;

    private Money totalAmounts;
    private Money price;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String cause;

    public Order(Long serverId, Long sellerId, Long ordererId, Long saleItemId, Money price, Integer quantity) {
        this.serverId = serverId;
        this.sellerId = sellerId;
        this.ordererId = ordererId;
        this.saleItemId = saleItemId;
        this.price = price;
        this.quantity = quantity;
        this.status = OrderStatus.WAIT;
        this.totalAmounts = price.multiply(quantity);
    }

    public void paymentFinish() {
        this.status = OrderStatus.COMPLETE;
    }

    public void paymentFail(String cause) {
        this.status = OrderStatus.REJECT;
        this.cause = cause;
    }
}
