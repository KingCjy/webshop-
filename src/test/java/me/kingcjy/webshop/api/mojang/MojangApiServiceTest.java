package me.kingcjy.webshop.api.mojang;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author KingCjy
 */
class MojangApiServiceTest {

    private MojangApiService mojangApiService;

    @BeforeEach
    public void setUp() {
        this.mojangApiService = new MojangApiService(
                new RestTemplate(),
                new ObjectMapper());
    }

    @Test
    public void authenticateTest() {
        AuthenticateResponse authenticateResponse = mojangApiService.authenticate("tlssycks@hanmail.net", "Zxcv720003!@#");
        System.out.println(authenticateResponse);
    }

    @Test
    public void invalidCredentialsTest() {
        assertThatThrownBy(() -> {
            AuthenticateResponse authenticateResponse = mojangApiService.authenticate("email", "password");
        }).isInstanceOf(MojangApiException.class);
    }
}