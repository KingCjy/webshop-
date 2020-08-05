package me.kingcjy.webshop.order.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author KingCjy
 */
public class OrderDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderRequest {
        @NotNull private Long saleItemId;
        @NotNull @Min(1) private Integer quantity;

        @Null private Long userId;

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
}
