package me.kingcjy.webshop.player.application;

import me.kingcjy.webshop.player.domain.Player;
import me.kingcjy.webshop.player.domain.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author KingCjy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerAccessServiceTest {

    @Autowired
    private PlayerAccessService playerAccessService;

    @Autowired
    private PlayerRepository playerRepository;

    private String uuid = "uuid";
    private String username = "KingCjy";
    private Long serverId = 1L;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void playerJoinTest() {
        PlayerDto.PlayerAccess playerAccess = new PlayerDto.PlayerAccess(uuid, username, serverId);
        playerAccessService.onPlayerJoin(playerAccess);

        Player player = playerRepository.findByServerIdAndUuid(serverId, uuid);

        assertThat(player).isNotNull();
        assertThat(player.getUuid()).isEqualTo(uuid);
        assertThat(player.getServerId()).isEqualTo(serverId);
    }

    @Test
    public void playerExitTest() {
        PlayerDto.PlayerAccess playerAccess = new PlayerDto.PlayerAccess(uuid, username, serverId);
        playerAccessService.onPlayerExit(playerAccess);

        Player player = playerRepository.findByServerIdAndUuid(serverId, uuid);

        assertThat(player).isNotNull();
        assertThat(player.getUuid()).isEqualTo(uuid);
        assertThat(player.getServerId()).isEqualTo(serverId);
    }
}