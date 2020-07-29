package me.kingcjy.webshop.player.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.player.domain.Player;
import me.kingcjy.webshop.player.domain.PlayerDto;
import me.kingcjy.webshop.player.domain.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class PlayerAccessService {

    private final PlayerRepository playerRepository;

    @Transactional
    public void onPlayerJoin(PlayerDto.PlayerAccess playerAccess) {
        Player player = playerRepository.findByServerIdAndUuid(playerAccess.getServerId(), playerAccess.getUuid());

    }

    public void onPlayerExit() {

    }

    public Player createPlayer(PlayerDto.PlayerAccess playerAccess) {
//        TODO

        return null;
    }
}
