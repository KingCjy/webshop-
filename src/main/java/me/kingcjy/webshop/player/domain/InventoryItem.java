package me.kingcjy.webshop.player.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author KingCjy
 */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryItem {

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "item_code")
    private Integer itemCode;

    @Column(name = "serialized_object")
    private String serializedObject;

    public InventoryItem(String name, String description, Integer quantity, Integer itemCode, String serializedObject) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.itemCode = itemCode;
        this.serializedObject = serializedObject;
    }
}
