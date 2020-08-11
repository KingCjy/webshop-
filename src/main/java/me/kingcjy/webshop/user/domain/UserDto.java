package me.kingcjy.webshop.user.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;

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

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UsernameChange {
        @NotBlank private String username;

        @Null private Long userId;

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class UserDetail {
        private String email;
        private String username;
    }
}
