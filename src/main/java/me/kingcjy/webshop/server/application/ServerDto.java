package me.kingcjy.webshop.server.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author KingCjy
 */
public class ServerDto {

    @Getter
    @AllArgsConstructor
    public static class ServerRegistration {
        @NotNull @NotEmpty private String name;
        @NotNull @NotEmpty private String version;
        @NotNull @NotEmpty private String domain;
        @NotNull @NotEmpty private Integer port;

        private Long ownerId;

        public void setOwnerId(Long ownerId) {
            this.ownerId = ownerId;
        }
    }
}
