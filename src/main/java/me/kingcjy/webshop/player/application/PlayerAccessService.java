package me.kingcjy.webshop.player.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.player.domain.AccessType;
import me.kingcjy.webshop.player.domain.Player;
import me.kingcjy.webshop.player.domain.PlayerRepository;
import me.kingcjy.webshop.user.domain.MojangUser;
import me.kingcjy.webshop.user.domain.User;
import me.kingcjy.webshop.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class PlayerAccessService {

    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    @Transactional
    public void onPlayerJoin(PlayerDto.PlayerAccess playerAccess) {
        Player player = findPlayer(playerAccess);
        player.updateLastPlayedAt();
        player.addAccessHistory(AccessType.JOIN);
    }

    @Transactional
    public void onPlayerExit(PlayerDto.PlayerAccess playerAccess) {
        Player player = findPlayer(playerAccess);
        player.addAccessHistory(AccessType.EXIT);
    }

    private Player findPlayer(PlayerDto.PlayerAccess playerAccess) {
        User user = userRepository.findByUUID(playerAccess.getUuid());

        if(user == null) {
            user = new User(playerAccess.getUsername(), new MojangUser(playerAccess.getUuid()));
            userRepository.save(user);
        }

        Player player = playerRepository.findByServerIdAndUuid(playerAccess.getServerId(), playerAccess.getUuid());

        if(player == null) {
            player = new Player(user.getId(), playerAccess.getServerId(), playerAccess.getUuid());
        }

        return playerRepository.save(player);
    }
}
