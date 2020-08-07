package me.kingcjy.webshop.order.application;

import lombok.*;
import me.kingcjy.webshop.common.model.Money;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author KingCjy
 */
public class OrderDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderRequest {
        @NotNull private Long saleItemId;
        @NotNull @Min(1) private Integer quantity;

        @Null private Long userId;

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    @Getter
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class WaitOrder {
        private Long orderId;
        private String sellerUuid;
        private String buyerUuid;
        private Integer quantity;
        private Integer totalAmounts;
        private String item;
        private LocalDateTime orderedAt;

        public WaitOrder(Long orderId, String sellerUuid, String buyerUuid, Integer quantity, Money totalAmounts, String item, LocalDateTime orderedAt) {
            this.orderId = orderId;
            this.sellerUuid = sellerUuid;
            this.buyerUuid = buyerUuid;
            this.quantity = quantity;
            this.totalAmounts = totalAmounts.getValue();
            this.item = item;
            this.orderedAt = orderedAt;
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class AcceptOrder {
        private List<Long> orderIds;

        @Null private Long serverId;

        public void setServerId(Long serverId) {
            this.serverId = serverId;
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class RejectOrder {
        private Long orderId;
        private String cause;

        @Null private Long serverId;

        public void setServerId(Long serverId) {
            this.serverId = serverId;
        }
    }
}
