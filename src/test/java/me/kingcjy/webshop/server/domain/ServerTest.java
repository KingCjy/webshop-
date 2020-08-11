package me.kingcjy.webshop.server.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @author KingCjy
 */
@DataJpaTest
public class ServerTest {

    @Autowired
    private ServerRepository serverRepository;

    @Test
    public void createServerTest() {
        String name = "킹짱왕서버";
        String description = "재밌다";
        String bannerImage = "이미";
        Version version = new Version("1.5.2");
        Address address = new Address("kingcjy.oa.to");
        SecretKey secretKey = new SecretKey("testtesttesttest");

        Server server = new Server(name, description, bannerImage, version, address, secretKey, 1L);
        serverRepository.save(server);
    }
}
