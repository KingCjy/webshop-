package me.kingcjy.webshop.atdd;

import me.kingcjy.webshop.common.model.ReturnId;
import me.kingcjy.webshop.order.application.OrderDto;
import me.kingcjy.webshop.player.application.PlayerDto;
import me.kingcjy.webshop.sale.application.SaleItemDto;
import me.kingcjy.webshop.server.application.ServerDto;
import me.kingcjy.webshop.user.domain.UserDto;
import me.kingcjy.webshop.util.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

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

//        Server Admin and buyer
        String email = "tlssycks@hanmail.net";
        String password = "Zxcv720003!@#";
        String username = "KingCjy";
        String uuid = "82532435-c58e-4917-adba-6f45e88546f0";

//        Seller
        String uuid2 = "82532435-c58e-4917-adba-6f45e88546f1";
        String username2 = "Seller";

        ReturnId userId = userWebSignUp(email, password, username);
        UserDto.UserToken userToken = userLogin(email, password);
        ReturnId serverId = createServer(userToken.getToken());
        String secretKey = getServerSecretKey(userToken.getToken(), serverId.getId());

        accessServer(secretKey, uuid2, username2);
        ReturnId sellingItemId = sellingItem(secretKey, uuid2);

        accessServer(secretKey, uuid, username);
        updateMoney(secretKey, uuid, username, 100000);
        ReturnId orderId = buyItem(sellingItemId.getId(), userToken.getToken());

        List<OrderDto.WaitOrder> waitOrders = findWaitOrders(secretKey);
        List<Long> orderIds = waitOrders.stream()
                .map(waitOrder -> waitOrder.getOrderId())
                .collect(Collectors.toList());
        acceptOrders(secretKey, orderIds);
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
        ServerDto.ServerRegistration serverRegistration = new ServerDto.ServerRegistration("KingCjy Server", "설명", "이미지", "12.20.1", "kingcjy.oa.to", 25565, null);

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

    private String getServerSecretKey(String userToken, Long serverId) {
        EntityExchangeResult<Response<ServerDto.ServerDetailResponse>> response = client.get()
                .uri("/api/v1/servers/{id}", serverId)
                .header("X-Auth-Token", userToken)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<Response<ServerDto.ServerDetailResponse>>() {})
                .returnResult();

        return response.getResponseBody().getBody().getSecretKey();
    }

    private void accessServer(String secretKey, String uuid2, String username2) {
        PlayerDto.PlayerAccess playerAccess = new PlayerDto.PlayerAccess(uuid2, username2, null);
        client.post()
                .uri("/plugin/api/v1/players/join")
                .header("X-Server-Key", secretKey)
                .body(Mono.just(playerAccess), PlayerDto.PlayerAccess.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult();
    }

    private ReturnId sellingItem(String secretKey, String uuid2) {
        SaleItemDto.SaleItemRequest saleItemRequest = new SaleItemDto.SaleItemRequest(uuid2, "집행검", "짱짱쎈 집행검이다", 10, 100000, "asdjkasdad", "http://naver.com", null);

        EntityExchangeResult<Response<ReturnId>> response = client.post()
                .uri("/plugin/api/v1/sale")
                .header("X-Server-Key", secretKey)
                .body(Mono.just(saleItemRequest), SaleItemDto.SaleItemRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(new ParameterizedTypeReference<Response<ReturnId>>() {})
                .returnResult();

        return response.getResponseBody().getBody();
    }

    private void updateMoney(String secretKey, String uuid, String username, int money) {
        PlayerDto.PlayerMoneyUpdate playerMoneyUpdate = new PlayerDto.PlayerMoneyUpdate(uuid, username, money, null);
        client.post()
                .uri("/plugin/api/v1/players/money")
                .header("X-Server-Key", secretKey)
                .body(Mono.just(playerMoneyUpdate), PlayerDto.PlayerAccess.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult();
    }

    private ReturnId buyItem(Long saleItemId, String token) {
        OrderDto.OrderRequest orderRequest = new OrderDto.OrderRequest(saleItemId, 1, null);
        EntityExchangeResult<Response<ReturnId>> response = client.post()
                .uri("/api/v1/orders")
                .header("X-Auth-Token", token)
                .body(Mono.just(orderRequest), PlayerDto.PlayerAccess.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(new ParameterizedTypeReference<Response<ReturnId>>() {})
                .returnResult();

        return response.getResponseBody().getBody();
    }

    private List<OrderDto.WaitOrder> findWaitOrders(String secretKey) {
        EntityExchangeResult<Response<List<OrderDto.WaitOrder>>> response = client.get()
                .uri("/plugin/api/v1/orders/wait")
                .header("X-Server-Key", secretKey)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<Response<List<OrderDto.WaitOrder>>>() {})
                .returnResult();
        return response.getResponseBody().getBody();
    }

    private void acceptOrders(String secretKey, List<Long> orderIds) {
        OrderDto.AcceptOrder acceptOrder = new OrderDto.AcceptOrder(orderIds, null);
        EntityExchangeResult<Response<ReturnId>> response = client.post()
                .uri("/plugin/api/v1/orders/accept")
                .header("X-Server-Key", secretKey)
                .body(Mono.just(acceptOrder), PlayerDto.PlayerAccess.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<Response<ReturnId>>() {})
                .returnResult();
    }
}
