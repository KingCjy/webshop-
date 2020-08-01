package me.kingcjy.webshop.server.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.exception.PermissionDeniedException;
import me.kingcjy.webshop.server.domain.Server;
import me.kingcjy.webshop.server.domain.ServerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class ServerDetailService {
    private final ServerRepository serverRepository;

    public ServerDto.ServerDetailResponse getServerDetail(Long serverId, Long ownerId) {
        Optional<Server> serverOptional = serverRepository.findById(serverId);

        if(serverOptional.isEmpty()) {
            throw new SeverNotFoundException(serverId, "잘못된 접근입니다.");
        }

        Server server = serverOptional.get();

        if(!ownerId.equals(server.getOwnerId())) {
            throw new PermissionDeniedException("권한이 없습니다.");
        }

        return new ServerDto.ServerDetailResponse(
                server.getName(),
                server.getVersion().getValue(),
                server.getAddress().getDomain(),
                server.getAddress().getPort(),
                server.getSecretKey().getValue());
    }
}
