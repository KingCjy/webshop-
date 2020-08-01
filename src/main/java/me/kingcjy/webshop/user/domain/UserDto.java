package me.kingcjy.webshop.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author KingCjy
 */
public class UserDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserLogin {
        @NotBlank private String email;
        @NotBlank private String password;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserToken {
        private String token;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserRegistration {
        @NotBlank private String email;
        @NotBlank private String password;
        @NotBlank private String username;
    }
}
