package me.kingcjy.webshop.sale.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @author KingCjy
 */
public class SaleItemDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SaleItemRequest {
        @NotBlank private String uuid;
        @NotBlank private String name;
        @NotBlank private String description;

        @Min(1)
        @NotNull private Integer quantity;

        @Min(1)
        @NotNull private Integer price;

        @NotBlank private String item;
        @NotBlank private String image;

        @Null private Long serverId;

        public void setServerId(Long serverId) {
            this.serverId = serverId;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SaleItemBuyRequest {
        @NotNull private Long saleItemId;
        @NotNull @Min(1) private Integer quantity;

        @Null private Long userId;

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
}
