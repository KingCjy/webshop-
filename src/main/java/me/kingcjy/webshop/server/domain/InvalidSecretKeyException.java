package me.kingcjy.webshop.server.domain;

import me.kingcjy.webshop.common.exception.WebException;
import org.springframework.http.HttpStatus;

/**
 * @author KingCjy
 */
public class InvalidSecretKeyException extends WebException {
    private String secretKey;

    public InvalidSecretKeyException(String secretKey, String message) {
        super(HttpStatus.FORBIDDEN, message);
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
