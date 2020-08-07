package me.kingcjy.webshop.order.domain;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.kingcjy.webshop.order.application.OrderDto;
import me.kingcjy.webshop.player.domain.QPlayer;
import me.kingcjy.webshop.sale.domain.QSaleItem;
import me.kingcjy.webshop.user.domain.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * @author KingCjy
 */
public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderRepositoryQuerydsl {

    private QOrder order = QOrder.order;
    private QSaleItem saleItem = QSaleItem.saleItem;
    private QPlayer buyer = new QPlayer("buyer");
    private QPlayer seller = new QPlayer("seller");

    private JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<OrderDto.WaitOrder> findWaitOrders(Long serverId) {
        return from(order)
                .join(saleItem).on(order.saleItemId.eq(saleItem.id))
                .join(seller).on(order.sellerId.eq(seller.id))
                .join(buyer).on(order.ordererId.eq(buyer.id))
                .where(
                        order.serverId.eq(serverId),
                        order.status.eq(OrderStatus.WAIT)
                )
                .select(Projections.constructor(OrderDto.WaitOrder.class,
                        order.id.as("orderId"),
                        seller.uuid.as("sellerUuid"),
                        buyer.uuid.as("buyerUuid"),
                        saleItem.quantity,
                        order.totalAmounts,
                        saleItem.item.item.as("item"),
                        order.createdAt.as("orderedAt")))
                .fetch();
    }
}
