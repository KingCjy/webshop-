package me.kingcjy.webshop.order.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.config.annotation.ServerId;
import me.kingcjy.webshop.order.application.OrderDto;
import me.kingcjy.webshop.order.application.OrderStatusService;
import me.kingcjy.webshop.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author KingCjy
 */
@RestController
@RequiredArgsConstructor
public class OrderStatusController {
    private final OrderStatusService orderStatusService;

    @GetMapping("/plugin/api/v1/orders/wait")
    public ResponseEntity<?> findWaitOrders(@ServerId Long serverId) {
        List<OrderDto.WaitOrder> waitOrders = orderStatusService.findWaitOrders(serverId);
        return Response.success(waitOrders);
    }

    @PatchMapping("/plugin/api/v1/orders/accept")
    public ResponseEntity<?> acceptOrders(@ServerId Long serverId, @RequestBody @Valid OrderDto.AcceptOrder acceptOrder) {
        acceptOrder.setServerId(serverId);
        orderStatusService.acceptOrders(acceptOrder);
        return Response.success();
    }

    @PatchMapping("/plugin/api/v1/orders/reject")
    public ResponseEntity<?> rejectOrders(@ServerId Long serverId, @RequestBody @Valid OrderDto.RejectOrder rejectOrder) {
        rejectOrder.setServerId(serverId);
        orderStatusService.rejectOrders(rejectOrder);
        return Response.success();
    }
}
