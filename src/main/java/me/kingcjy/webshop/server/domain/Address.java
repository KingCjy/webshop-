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
public class Address {

    public static final Integer DEFAULT_SERVER_PORT = 25565;

    @Column(name = "domain")
    private String domain;

    @Column(name = "port")
    private Integer port;

    public Address(String domain) {
        this(domain, DEFAULT_SERVER_PORT);
    }

    public Address(String domain, Integer port) {
        this.domain = domain;
        this.port = port;
    }

    public String getFullAddress() {
        return domain + ":"  + port;
    }
}
