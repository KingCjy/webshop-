package me.kingcjy.webshop.sale.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author KingCjy
 */
public class SaleItemDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SaleItemRequest {
        @NotNull @NotEmpty private String uuid;
        @NotNull @NotEmpty private String name;
        @NotNull @NotEmpty private String description;
        @NotNull private Integer quantity;
        @NotNull private Integer price;
        @NotNull @NotEmpty private String item;
        @NotNull @NotEmpty private String image;
    }
}
