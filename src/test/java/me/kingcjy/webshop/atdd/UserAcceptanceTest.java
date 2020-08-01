package me.kingcjy.webshop.atdd;

import me.kingcjy.webshop.common.model.ReturnId;
import me.kingcjy.webshop.player.application.PlayerDto;
import me.kingcjy.webshop.server.application.ServerDto;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.util.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * @author KingCjy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAcceptanceTest {

    @Autowired
    private WebTestClient client;

    @Test
    @DisplayName("웹 회원가입 -> 서버 생성 -> 플레이어 서버접속 -> 플레이어 웹으로 정보입력")
    public void playerRegistrationTest() {

        String email = "tlssycks@hanmail.net";
        String password = "Zxcv720003!@#";
        String username = "KingCjy";
        String uuid = "82532435-c58e-4917-adba-6f45e88546f0";

        ReturnId userId = userWebSignUp(email, password, username);
        UserDto.UserToken userToken = userLogin(email, password);
        ReturnId serverId = createServer(userToken.getToken());

//        PlayerDto.PlayerAccess playerAccess = new PlayerDto.PlayerAccess(uuid, username, null);
//
//        EntityExchangeResult<Response<ReturnId>> response = client.post()
//                .uri("/api/v1/players/join")
//                .body(Mono.just(playerAccess), PlayerDto.PlayerAccess.class)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(new ParameterizedTypeReference<Response<ReturnId>>() {})
//                .returnResult();
//
//
//        UserDto.UserRegistration userRegistration = new UserDto.UserRegistration("tlssycks@hanmail.net", "Zxcv720003!@#", username);
//        client.post()
//                .uri("/api/v1/users")
//                .body(Mono.just(userRegistration), UserDto.UserRegistration.class)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody()
//                .returnResult();
    }

    private ReturnId userWebSignUp(String email, String password, String username) {
        UserDto.UserRegistration userRegistration = new UserDto.UserRegistration(email, password, username);

        EntityExchangeResult<Response<ReturnId>> response = client.post()
                .uri("/api/v1/users")
                .body(Mono.just(userRegistration), UserDto.UserRegistration.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(new ParameterizedTypeReference<Response<ReturnId>>() {})
                .returnResult();

        return response.getResponseBody().getBody();
    }

    private UserDto.UserToken userLogin(String email, String password) {
        UserDto.UserLogin userLogin = new UserDto.UserLogin(email, password);

        EntityExchangeResult<Response<UserDto.UserToken>> response = client.post()
                .uri("/api/v1/users/login")
                .body(Mono.just(userLogin), UserDto.UserRegistration.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<Response<UserDto.UserToken>>() {})
                .returnResult();

        return response.getResponseBody().getBody();
    }

    private ReturnId createServer(String token) {
        ServerDto.ServerRegistration serverRegistration = new ServerDto.ServerRegistration("최재용서버", "12.20.1", "kingcjy.oa.to", -1, null);

        EntityExchangeResult<Response<ReturnId>> response = client.post()
                .uri("/api/v1/servers")
                .header("X-Auth-Token", token)
                .body(Mono.just(serverRegistration), PlayerDto.PlayerAccess.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(new ParameterizedTypeReference<Response<ReturnId>>() {})
                .returnResult();

        return response.getResponseBody().getBody();
    }
}
