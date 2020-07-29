package me.kingcjy.webshop.user.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author KingCjy
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    @Column(name = "password")
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public boolean match(String password) {
        return this.value.equals(password);
    }
}
