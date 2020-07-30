package me.kingcjy.webshop.server.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author KingCjy
 */
@DataJpaTest
class ServerRepositoryTest {

    @Autowired
    private ServerRepository serverRepository;

    private String key = "secret1234";

    @BeforeEach
    public void setUp() {
        String name = "킹짱왕서버";
        Version version = new Version("1.5.2");
        Address address = new Address("kingcjy.oa.to");
        SecretKey secretKey = new SecretKey(key);

        Server server = new Server(name, version, address, secretKey, 1L);
        serverRepository.save(server);
    }

    @Test
    public void findServerIdBySecretKey() {
        Long id = serverRepository.findServerIdBySecretKey(key);
        assertThat(id).isNotNull();
    }
}