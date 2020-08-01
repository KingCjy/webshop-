package me.kingcjy.webshop.user.application;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author KingCjy
 */
@Getter
public class EmailAlreadyUsedException extends RuntimeException {

    private String email;

    public EmailAlreadyUsedException(@NotNull @NotEmpty String email, String message) {
        super(message);
        this.email = email;
    }
}
