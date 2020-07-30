package me.kingcjy.webshop.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author KingCjy
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MojangUser {

    @Column(name = "uuid")
    private String uuid;

    public MojangUser(String uuid) {
        this.uuid = uuid;
    }
}
