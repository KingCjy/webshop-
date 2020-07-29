package me.kingcjy.webshop.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author KingCjy
 */
public class UserDto {

    @Getter
    @AllArgsConstructor
    public static class UserRegistration {
        @NotNull @NotEmpty private String email;
        @NotNull @NotEmpty private String password;
        @NotNull @NotEmpty private String username;
    }
}
