package me.kingcjy.webshop.util;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

/**
 * @author KingCjy
 */
@Getter
public class Response<T> {
    public static final String SUCCESS_MESSAGE = "success";

    private Meta meta;
    private T body;

    @Data
    public static class Meta {
        private String path;
        private Integer status;
        private String message;
        private LocalDateTime timestamp;
    }

    private Response() {}

    private Response(HttpStatus httpStatus, String message, T body) {
        this.meta = new Meta();
        this.meta.setStatus(httpStatus.value());
        this.meta.setMessage(message);
        this.meta.setTimestamp(LocalDateTime.now());
        this.meta.setPath(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI());

        this.body = body;
    }

    public static <T> ResponseEntity<T> success() {
        return getResponse(HttpStatus.OK, SUCCESS_MESSAGE, null);
    }

    public static <T> ResponseEntity<T> success(T body) {
        return getResponse(HttpStatus.OK, SUCCESS_MESSAGE, body);
    }

    public static <T> ResponseEntity<T> created(T body, String location) {
        ResponseUtil.setContentLocation(location);
        return getResponse(HttpStatus.CREATED, SUCCESS_MESSAGE, body);
    }

    public static <T> ResponseEntity<?> noContent(String message) {
        return getResponse(HttpStatus.NO_CONTENT, message, null);
    }

    public static <T> ResponseEntity<?> badRequest(String message) {
        return getResponse(HttpStatus.BAD_REQUEST, message, null);
    }

    public static <T> ResponseEntity<?> unauthorized(String message) {
        return getResponse(HttpStatus.UNAUTHORIZED, message, null);
    }

    public static <T> ResponseEntity<?> forbidden(String message) {
        return getResponse(HttpStatus.FORBIDDEN, message, null);
    }

    public static <T> ResponseEntity<?> getResponse(HttpStatus httpStatus, String message) {
        return getResponse(httpStatus, message, null);
    }

    public static <T> ResponseEntity<T> getResponse(HttpStatus httpStatus, String message, T body) {
        return new ResponseEntity<T>((T)new Response<>(httpStatus, message, body), httpStatus);
    }
}
