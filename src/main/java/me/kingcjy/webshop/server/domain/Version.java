package me.kingcjy.webshop.server.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author KingCjy
 */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class Version {

    @Column(name = "server_version")
    private String value;

    public Version(String value) {
        this.value = value;
    }
}
