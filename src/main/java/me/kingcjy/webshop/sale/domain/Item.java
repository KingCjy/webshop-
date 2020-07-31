package me.kingcjy.webshop.sale.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author KingCjy
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class Item {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "item")
    private String item;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    public Item(String name, String description, String image, String item) {
        this(name, description, image, item, PaymentType.ITEM);
    }

    public Item(String name, String description, String image, String item, PaymentType paymentType) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.item = item;
        this.paymentType = paymentType;
    }
}
