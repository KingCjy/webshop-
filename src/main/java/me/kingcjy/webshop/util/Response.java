package me.kingcjy.webshop.util;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
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
    private class Meta {
        private String path;
        private Integer status;
        private String message;
        private LocalDateTime timestamp;
    }

    public Response(HttpStatus httpStatus, String message, T body) {
        this.meta = new Meta();
        this.meta.setStatus(httpStatus.value());
        this.meta.setMessage(message);
        this.meta.setTimestamp(LocalDateTime.now());
        this.meta.setPath(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI());

        this.body = body;
    }

    public static <T> Response<T> success() {
        return getResponse(HttpStatus.OK, SUCCESS_MESSAGE, null);
    }

    public static <T> Response<T> created(T body, String location) {
        ResponseUtil.setContentLocation(location);
        return getResponse(HttpStatus.CREATED, SUCCESS_MESSAGE, body);
    }

    public static <T> Response<?> badRequest(String message) {
        return getResponse(HttpStatus.BAD_REQUEST, message, null);
    }

    public static <T> Response<T> success(T body) {
        return getResponse(HttpStatus.OK, SUCCESS_MESSAGE, body);
    }

    private static <T> Response<T> getResponse(HttpStatus httpStatus, String message, T body) {
        return new Response<>(httpStatus, message, body);
    }
}
