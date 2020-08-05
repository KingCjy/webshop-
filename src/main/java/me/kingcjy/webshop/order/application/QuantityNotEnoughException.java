package me.kingcjy.webshop.order.application;

import me.kingcjy.webshop.common.exception.WebException;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author KingCjy
 */
public class QuantityNotEnoughException extends WebException {
    private Integer itemQuantity;
    private Integer requestQuantity;

    public QuantityNotEnoughException(Integer itemQuantity, Integer requestQuantity, String message) {
        super(HttpStatus.BAD_REQUEST, message);
        this.itemQuantity = itemQuantity;
        this.requestQuantity = requestQuantity;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public Integer getRequestQuantity() {
        return requestQuantity;
    }
}
