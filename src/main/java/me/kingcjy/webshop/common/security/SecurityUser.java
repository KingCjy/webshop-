package me.kingcjy.webshop.common.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author KingCjy
 */
@Getter
@AllArgsConstructor
public class SecurityUser {
    private Long userId;
    private String uuid;
    private String email;
}
