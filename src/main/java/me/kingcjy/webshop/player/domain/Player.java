package me.kingcjy.webshop.player.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kingcjy.webshop.common.jpa.MoneyConverter;
import me.kingcjy.webshop.common.model.Money;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KingCjy
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    private Long userId;
    private Long serverId;
    private String uuid;

    @Convert(converter = MoneyConverter.class)
    private Money money;

    @ElementCollection
    @CollectionTable(name = "inventory_item", joinColumns = @JoinColumn(name = "player_id"))
    @OrderColumn(name = "inventory_item_id")
    private List<InventoryItem> inventoryItems;

    private LocalDateTime lastPlayedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Player(Long userId, Long serverId) {
        this(userId, serverId, new Money(0), new ArrayList<>());
    }

    public Player(Long userId, Long serverId, Money money, List<InventoryItem> inventoryItems) {
        this.userId = userId;
        this.serverId = serverId;
        this.money = money;
        this.inventoryItems = inventoryItems;
    }

    public void updateLastPlayedAt() {
        this.lastPlayedAt = LocalDateTime.now();
    }
}
