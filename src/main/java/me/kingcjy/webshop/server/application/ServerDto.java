package me.kingcjy.webshop.server.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @author KingCjy
 */
public class ServerDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ServerRegistration {
        @NotBlank private String name;
        @NotBlank private String version;
        @NotBlank private String domain;

        @Min(0)
        @Max(65565)
        private Integer port;

        private Long ownerId;

        public void setOwnerId(Long ownerId) {
            this.ownerId = ownerId;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ServerDetailResponse {
        private String name;
        private String version;
        private String domain;
        private Integer port;
        private String secretKey;
    }
}
