package me.kingcjy.webshop.order.application;

import me.kingcjy.webshop.common.exception.WebException;
import me.kingcjy.webshop.common.model.Money;
import org.springframework.http.HttpStatus;

/**
 * @author KingCjy
 */
public class MoneyNotEnoughException extends WebException {
    private Money totalAmounts;
    private Money money;

    public MoneyNotEnoughException(Money totalAmounts, Money money, String message) {
        super(HttpStatus.BAD_REQUEST, message);
        this.totalAmounts = totalAmounts;
        this.money = money;
    }
}
