package me.kingcjy.webshop.common.exception;

/**
 * @author KingCjy
 */
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
