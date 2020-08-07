package me.kingcjy.webshop.common.exception;

import me.kingcjy.webshop.api.mojang.MojangApiException;
import me.kingcjy.webshop.server.domain.InvalidSecretKeyException;
import me.kingcjy.webshop.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingCjy
 */
@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> errorList = new ArrayList<>(e.getBindingResult().getFieldErrors());

        if(errorList.size() > 0) {
            FieldError error = errorList.get(0);
            String message = error.getField() + "은(는) " + error.getDefaultMessage();

            return Response.badRequest(message);
        } else {
            return Response.badRequest(e.getMessage());
        }
    }

    @ExceptionHandler(WebException.class)
    public ResponseEntity<?> handleWebException(WebException e) {
        return Response.getResponse(e.getHttpStatus(), e.getMessage());
    }
}
