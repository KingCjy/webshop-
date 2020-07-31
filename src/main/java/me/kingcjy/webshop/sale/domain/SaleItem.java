package me.kingcjy.webshop.sale.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kingcjy.webshop.common.jpa.MoneyConverter;
import me.kingcjy.webshop.common.model.Money;

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

    private Long sellerId;

    @Embedded
    private Item item;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    private Integer quantity;

    public SaleItem(Long sellerId, Item item, Money price, Integer quantity) {
        this.sellerId = sellerId;
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }
}
