package me.kingcjy.webshop.player.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * @author KingCjy
 */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessHistory {

    @Column(name = "access_type")
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public AccessHistory(AccessType accessType) {
        this.accessType = accessType;
    }
}
