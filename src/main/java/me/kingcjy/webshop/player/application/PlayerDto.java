package me.kingcjy.webshop.player.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author KingCjy
 */
public class PlayerDto {

    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerAccess {
        @NotBlank private String uuid;
        @NotBlank private String username;

        private Long serverId;

        public void setServerId(Long serverId) {
            this.serverId = serverId;
        }
    }

    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerMoneyUpdate {
        @NotBlank private String uuid;
        @NotBlank private String username;
        @Min(0) private Integer money;

        private Long serverId;

        public void setServerId(Long serverId) {
            this.serverId = serverId;
        }
    }
}
