package me.kingcjy.webshop.api.mojang;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author KingCjy
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String error;
    @JsonProperty("errorMessage")
    private String errorMessage;
    private String cause;
}
