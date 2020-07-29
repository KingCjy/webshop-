package me.kingcjy.webshop.common.jpa;

import me.kingcjy.webshop.common.model.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author KingCjy
 */
@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Money money) {
        return money == null ? null : money.getValue();
    }

    @Override
    public Money convertToEntityAttribute(Integer value) {
        return value == null ? null : new Money(value);
    }
}
