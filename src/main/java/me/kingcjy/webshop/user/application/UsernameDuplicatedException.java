package me.kingcjy.webshop.user.application;

import me.kingcjy.webshop.common.exception.WebException;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;

/**
 * @author KingCjy
 */
public class UsernameDuplicatedException extends WebException {
    private String username;

    public UsernameDuplicatedException(String username, String message) {
        super(HttpStatus.BAD_REQUEST, message);
        this.username = username;
    }
}
