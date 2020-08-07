package me.kingcjy.webshop.order.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.order.domain.Order;
import me.kingcjy.webshop.order.domain.OrderRepository;
import me.kingcjy.webshop.order.domain.OrderStatus;
import me.kingcjy.webshop.player.domain.Player;
import me.kingcjy.webshop.player.domain.PlayerRepository;
import me.kingcjy.webshop.sale.domain.SaleItem;
import me.kingcjy.webshop.sale.domain.SaleItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class OrderStatusService {
    private final OrderRepository orderRepository;
    private final PlayerRepository playerRepository;
    private final SaleItemRepository saleItemRepository;

    @Transactional(readOnly = true)
    public List<OrderDto.WaitOrder> findWaitOrders(Long serverId) {
        List<OrderDto.WaitOrder> waitOrders = orderRepository.findWaitOrders(serverId);
        return waitOrders;
    }

    @Transactional
    public void acceptOrders(OrderDto.AcceptOrder acceptOrder) {
        List<Order> orders = orderRepository.findByServerIdAndIdIn(acceptOrder.getServerId(), acceptOrder.getOrderIds());

        for (Order order : orders) {
            Optional<Player> sellerOptional = playerRepository.findById(order.getSellerId());

            if(sellerOptional.isEmpty()) {
                continue;
            }

            sellerOptional.get().sell(order.getTotalAmounts());
            order.paymentFinish();
        }
    }

    @Transactional
    public void rejectOrders(OrderDto.RejectOrder rejectOrder) {
        Order order = orderRepository.findByIdAndServerId(rejectOrder.getOrderId(), rejectOrder.getServerId());

        Player orderer = playerRepository.findById(order.getOrdererId()).orElse(null);
        SaleItem saleItem = saleItemRepository.findById(order.getSaleItemId()).orElse(null);

        if(saleItem == null || orderer == null || OrderStatus.WAIT != order.getStatus()) {
            throw new RejectOrderFailException("유효하지 않은 주문입니다.");
        }

        saleItem.addQuantity(order.getQuantity());
        order.paymentFail(rejectOrder.getCause());
        orderer.plusMoney(order.getTotalAmounts());
    }
}
