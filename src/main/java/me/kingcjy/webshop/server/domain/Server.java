package me.kingcjy.webshop.server.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author KingCjy
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "server_id")
    private Long id;

    private String name;

    @Embedded
    private Version version;

    @Embedded
    private Address address;

    @Embedded
    private SecretKey secretKey;

    private Long ownerId;

    public Server(String name, Version version, Address address, SecretKey secretKey, Long ownerId) {
        this.name = name;
        this.version = version;
        this.address = address;
        this.secretKey = secretKey;
        this.ownerId = ownerId;
    }
}
