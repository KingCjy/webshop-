package me.kingcjy.webshop.common.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author KingCjy
 */

@ToString
@EqualsAndHashCode
public class Money {
    private int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
