package me.kingcjy.webshop.api.mojang;

import lombok.Getter;

/**
 * @author KingCjy
 */
@Getter
public class AuthenticateRequest {
    private Agent agent = new Agent();
    private String username;
    private String password;
    private Boolean requireUser = true;

    public AuthenticateRequest(String email, String password) {
        this.username = email;
        this.password = password;
    }

    @Getter
    private static class Agent {
        private String name = "Minecraft";
        private Integer version = 1;
    }
}
