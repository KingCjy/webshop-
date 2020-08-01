package me.kingcjy.webshop.user.application;

import me.kingcjy.webshop.common.exception.WebException;
import org.springframework.http.HttpStatus;

/**
 * @author KingCjy
 */
public class LoginFailException extends WebException {
    public LoginFailException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
