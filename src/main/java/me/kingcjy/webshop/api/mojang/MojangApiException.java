package me.kingcjy.webshop.api.mojang;

import lombok.Getter;
import me.kingcjy.webshop.common.exception.WebException;
import org.springframework.http.HttpStatus;

/**
 * @author KingCjy
 */
@Getter
public class MojangApiException extends WebException {

    public MojangApiException(String errorMessage, RuntimeException e) {
        super(HttpStatus.FORBIDDEN, errorMessage, e);
    }

    public MojangApiException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
