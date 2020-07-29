package me.kingcjy.webshop.server.application;

import me.kingcjy.webshop.server.domain.SecretKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author KingCjy
 */
@SpringBootTest
class SecretKeyGenerateServiceTest {

    @Autowired
    private SecretKeyGenerateService secretKeyGenerateService;

    @Test
    public void generateKeyTest() {
        SecretKey secretKey = secretKeyGenerateService.generate("TEST1234");
    }
}