package me.kingcjy.webshop.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author KingCjy
 */
public class PermissionDeniedException extends WebException {
    public PermissionDeniedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
