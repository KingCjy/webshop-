package me.kingcjy.webshop.order.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.model.Money;
import me.kingcjy.webshop.order.domain.Order;
import me.kingcjy.webshop.order.domain.OrderRepository;
import me.kingcjy.webshop.player.domain.Player;
import me.kingcjy.webshop.player.domain.PlayerRepository;
import me.kingcjy.webshop.sale.domain.SaleItem;
import me.kingcjy.webshop.sale.domain.SaleItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class OrderCreateService {
    private final OrderRepository orderRepository;
    private final PlayerRepository playerRepository;
    private final SaleItemRepository saleItemRepository;

    @Transactional
    public Long createOrder(OrderDto.OrderRequest orderRequest) {
        SaleItem saleItem = saleItemRepository.findById(orderRequest.getSaleItemId()).orElseThrow();

        Player buyer = playerRepository.findByServerIdAndUserId(saleItem.getServerId(), orderRequest.getUserId());

        if(buyer == null) {
            throw new InvalidServerPlayerException(orderRequest.getUserId(), "해당 서버의 플레이어가 아닙니다.");
        }

        if(saleItem.getQuantity() < orderRequest.getQuantity()) {
            throw new QuantityNotEnoughException(saleItem.getQuantity(), orderRequest.getQuantity(), saleItem.getQuantity() + " 개 이하 구매가 가능합니다.");
        }

        Money totalAmounts = saleItem.getPrice().multiply(orderRequest.getQuantity());

        if(!buyer.getMoney().isGreaterThan(totalAmounts)) {
            throw new MoneyNotEnoughException(totalAmounts, buyer.getMoney(), totalAmounts.minus(buyer.getMoney()).getValue() + " 원이 더 필요합니다.");
        }

        Order order = new Order(buyer.getServerId(), saleItem.getSellerId(), buyer.getId(), saleItem.getId(), saleItem.getPrice(), orderRequest.getQuantity());
        orderRepository.save(order);

        saleItem.sell(orderRequest.getQuantity());
        buyer.buy(order.getTotalAmounts());
        return order.getId();
    }
}
