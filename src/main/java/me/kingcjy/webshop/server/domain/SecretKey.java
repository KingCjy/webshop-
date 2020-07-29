package me.kingcjy.webshop.server.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author KingCjy
 */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SecretKey {

    @Column(name = "secret_key")
    private String value;

    public SecretKey(String value) {
        this.value = value;
    }
}
