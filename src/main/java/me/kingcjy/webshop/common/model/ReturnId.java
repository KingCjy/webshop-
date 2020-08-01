package me.kingcjy.webshop.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author KingCjy
 */
@Getter
@NoArgsConstructor
public class ReturnId {
    private Long id;

    private ReturnId(Long id) {
        this.id = id;
    }

    public static ReturnId from(Long id) {
        return new ReturnId(id);
    }
}
