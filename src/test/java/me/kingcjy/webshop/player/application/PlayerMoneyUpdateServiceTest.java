package me.kingcjy.webshop.player.application;

import me.kingcjy.webshop.common.model.Money;
import me.kingcjy.webshop.player.domain.Player;
import me.kingcjy.webshop.player.domain.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author KingCjy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerMoneyUpdateServiceTest {

    @Autowired
    private PlayerMoneyUpdateService playerMoneyUpdateService;

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setUp() {
        Player player = new Player(1L, 1L, "uuid");
        playerRepository.save(player);
    }

    @Test
    public void updateMoneyTest() {
        Money money = new Money(1000);
        PlayerDto.PlayerMoneyUpdate playerMoneyUpdate = new PlayerDto.PlayerMoneyUpdate("uuid", "username", money.getValue(), 1L);
        playerMoneyUpdateService.updateMoney(playerMoneyUpdate);

        Player player = playerRepository.findByServerIdAndUuid(1L, "uuid");

        assertThat(player).isNotNull();
        assertThat(player.getMoney()).isEqualTo(money);
    }
}