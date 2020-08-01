package me.kingcjy.webshop.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author KingCjy
 */
@Getter
public class DataNotFoundException extends WebException {

    private Long id;

    public DataNotFoundException(Long id, String message) {
        super(HttpStatus.NO_CONTENT, message);
        this.id = id;
    }
}
