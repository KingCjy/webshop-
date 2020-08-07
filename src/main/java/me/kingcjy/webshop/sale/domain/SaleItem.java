package me.kingcjy.webshop.sale.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kingcjy.webshop.common.jpa.MoneyConverter;
import me.kingcjy.webshop.common.model.Money;
import me.kingcjy.webshop.order.application.QuantityNotEnoughException;

import javax.persistence.*;

/**
 * @author KingCjy
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private Long serverId;
    private Long sellerId;

    @Embedded
    private Item item;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private SaleItemStatus status;

    public SaleItem(Long serverId, Long sellerId, Item item, Money price, Integer quantity) {
        this.serverId = serverId;
        this.sellerId = sellerId;
        this.item = item;
        this.price = price;
        this.quantity = quantity;
        this.status = SaleItemStatus.SELLING;
    }

    public void sell(Integer quantity) {
        if(this.quantity < quantity) {
            throw new QuantityNotEnoughException(this.quantity, quantity, this.quantity + " 개 이하 구매가 가능합니다.");
        }
        this.quantity -= quantity;
    }

    public void addQuantity(Integer quantity) {
        this.quantity += quantity;
    }
}
