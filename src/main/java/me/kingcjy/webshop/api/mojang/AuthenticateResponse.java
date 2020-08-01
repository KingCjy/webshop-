package me.kingcjy.webshop.api.mojang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author KingCjy
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateResponse {
    private String accessToken;
    private SelectedProfile selectedProfile;
}
