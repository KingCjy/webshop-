package me.kingcjy.webshop.order.application;

import me.kingcjy.webshop.common.exception.WebException;
import org.springframework.http.HttpStatus;

/**
 * @author KingCjy
 */
public class RejectOrderFailException extends WebException {
    public RejectOrderFailException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
