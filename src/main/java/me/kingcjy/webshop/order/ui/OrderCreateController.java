package me.kingcjy.webshop.order.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.model.ReturnId;
import me.kingcjy.webshop.common.security.SecurityUser;
import me.kingcjy.webshop.order.application.OrderCreateService;
import me.kingcjy.webshop.order.application.OrderDto;
import me.kingcjy.webshop.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author KingCjy
 */
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderCreateController {
    private final OrderCreateService orderCreateService;

    @PostMapping
    public ResponseEntity<?> createOrder(
            SecurityUser securityUser,
            @RequestBody @Valid OrderDto.OrderRequest orderRequest) {
        orderRequest.setUserId(securityUser.getUserId());
        Long orderId = orderCreateService.createOrder(orderRequest);
        return Response.created(ReturnId.from(orderId), "");
    }
}
