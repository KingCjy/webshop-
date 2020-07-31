package me.kingcjy.webshop.server.application;

import me.kingcjy.webshop.server.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author KingCjy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServerRegistrationServiceTest {

    @Autowired
    private ServerRegistrationService serverRegistrationService;

    @Autowired
    private ServerRepository serverRepository;

    @Test
    public void registrationServerTest() {
        String name = "킹짱왕서버";
        String version = "1.5.2";
        String domain = "kingcjy.oa.to";
        Integer port = Address.DEFAULT_SERVER_PORT;
        Long ownerId = 1L;
        ServerDto.ServerRegistration serverRegistration = new ServerDto.ServerRegistration(name, version, domain, port, ownerId);

        Long id = serverRegistrationService.registration(serverRegistration);

        Server server = serverRepository.findById(id).get();

        assertThat(server).isNotNull();
        assertThat(server.getName()).isEqualTo(name);
        assertThat(server.getVersion()).isEqualTo(new Version(version));
        assertThat(server.getAddress()).isEqualTo(new Address(domain, port));
        assertThat(server.getOwnerId()).isEqualTo(ownerId);
    }
}