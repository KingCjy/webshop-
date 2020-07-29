package me.kingcjy.webshop.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author KingCjy
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    @Embedded
    private Password password;

    private String username;

    @Embedded
    private MojangUser mojangUser;

    private boolean emailCertified;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public User(String email, String password, String username) {
        this(email, password, username, new MojangUser());
    }

    public User(String email, String password, String username, MojangUser mojangUser) {
        this.email = email;
        this.password = new Password(password);
        this.username = username;
        this.mojangUser = mojangUser;
        this.emailCertified = true;
    }
}
