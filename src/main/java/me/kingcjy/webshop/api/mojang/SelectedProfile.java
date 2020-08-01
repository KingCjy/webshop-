package me.kingcjy.webshop.api.mojang;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * @author KingCjy
 */
@Getter
@ToString
public class SelectedProfile {
    @JsonProperty("id")
    private String uuid;
    private String name;
    private long createdAt;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
