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

    public Money multiply(Integer multiple) {
        return new Money(value * multiple);
    }

    public boolean isGreaterThan(Money money) {
        return this.value >= money.value;
    }

    public Money minus(Money minus) {
        return new Money(this.value - minus.value);
    }

    public Money plus(Money money) {
        return new Money(this.value + money.value);
    }
}
