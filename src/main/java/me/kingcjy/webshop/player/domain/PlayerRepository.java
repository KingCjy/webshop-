package me.kingcjy.webshop.player.domain;

import me.kingcjy.webshop.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author KingCjy
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByServerIdAndUuid(Long serverId, String uuid);
}
