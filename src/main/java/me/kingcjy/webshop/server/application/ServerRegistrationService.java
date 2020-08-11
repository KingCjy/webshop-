package me.kingcjy.webshop.server.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.server.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class ServerRegistrationService {

    private final ServerRepository serverRepository;
    private final SecretKeyGenerateService secretKeyGenerateService;

    @Transactional
    public Long registration(ServerDto.ServerRegistration registration) {
        SecretKey secretKey = secretKeyGenerateService.generate(registration.getName());

        Server server = new Server(
                registration.getName(),
                registration.getDescription(),
                registration.getBannerImage(),
                new Version(registration.getVersion()),
                new Address(registration.getDomain(), registration.getPort()),
                secretKey,
                registration.getOwnerId()
        );

        serverRepository.save(server);
        return server.getId();
    }
}
