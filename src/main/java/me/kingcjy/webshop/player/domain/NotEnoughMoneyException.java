package me.kingcjy.webshop.player.domain;

import me.kingcjy.webshop.common.exception.WebException;
import org.springframework.http.HttpStatus;

/**
 * @author KingCjy
 */
public class NotEnoughMoneyException extends WebException {
    public NotEnoughMoneyException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
