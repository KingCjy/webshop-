package me.kingcjy.webshop.order.application;


import me.kingcjy.webshop.common.exception.WebException;
import org.springframework.http.HttpStatus;

/**
 * @author KingCjy
 */
public class InvalidServerPlayerException extends WebException {
    private Long userId;
    public InvalidServerPlayerException(Long userId, String message) {
        super(HttpStatus.BAD_REQUEST, message);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
