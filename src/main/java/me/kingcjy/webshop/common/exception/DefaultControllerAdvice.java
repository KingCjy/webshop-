package me.kingcjy.webshop.common.exception;

import me.kingcjy.webshop.server.domain.InvalidSecretKeyException;
import me.kingcjy.webshop.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

            return new ResponseEntity<>(Response.badRequest(message), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(Response.badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(InvalidSecretKeyException.class)
    public ResponseEntity<?> handleRuntimeException(InvalidSecretKeyException invalidSecretKeyException) {
        return new ResponseEntity<>(Response.badRequest(invalidSecretKeyException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
