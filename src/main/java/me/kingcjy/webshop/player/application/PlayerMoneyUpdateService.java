package me.kingcjy.webshop.player.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.model.Money;
import me.kingcjy.webshop.player.domain.Player;
import me.kingcjy.webshop.player.domain.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class PlayerMoneyUpdateService {
    private final PlayerRepository playerRepository;
    private final PlayerAccessService playerAccessService;

    @Transactional
    public void updateMoney(PlayerDto.PlayerMoneyUpdate playerMoneyUpdate) {

        if(!isPlayerExist(playerMoneyUpdate.getServerId(), playerMoneyUpdate.getUuid())) {
            playerAccessService.onPlayerJoin(new PlayerDto.PlayerAccess(playerMoneyUpdate.getUuid(), playerMoneyUpdate.getUsername(), playerMoneyUpdate.getServerId()));
        }

        Player player = playerRepository.findByServerIdAndUuid(playerMoneyUpdate.getServerId(), playerMoneyUpdate.getUuid());
        player.updateMoney(new Money(playerMoneyUpdate.getMoney()));
    }

    private boolean isPlayerExist(Long serverId, String uuid) {
        Player player = playerRepository.findByServerIdAndUuid(serverId, uuid);
        return player != null;
    }
}
