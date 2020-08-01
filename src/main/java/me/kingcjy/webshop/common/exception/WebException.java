package me.kingcjy.webshop.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author KingCjy
 */
public class WebException extends RuntimeException {
    private HttpStatus httpStatus;

    public WebException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public WebException(HttpStatus httpStatus, String message, Throwable throwable) {
        super(message, throwable);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
