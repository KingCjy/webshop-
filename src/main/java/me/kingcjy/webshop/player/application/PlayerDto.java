package me.kingcjy.webshop.player.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author KingCjy
 */
public class PlayerDto {

    @Getter
    @AllArgsConstructor
    @ToString
    public static class PlayerAccess {
        @NotNull @NotEmpty private String uuid;
        @NotNull @NotEmpty private String username;

        private Long serverId;

        public void setServerId(Long serverId) {
            this.serverId = serverId;
        }
    }
}
