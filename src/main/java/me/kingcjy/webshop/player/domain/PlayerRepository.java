package me.kingcjy.webshop.player.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author KingCjy
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByServerIdAndUuid(Long serverId, String uuid);
}
