package me.kingcjy.webshop.player.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author KingCjy
 */
public class PlayerDto {

    @Getter
    @AllArgsConstructor
    public static class PlayerAccess {
        @NotNull @NotEmpty private String uuid;
        @NotNull @NotEmpty private String name;

        private Long serverId;
    }
}
