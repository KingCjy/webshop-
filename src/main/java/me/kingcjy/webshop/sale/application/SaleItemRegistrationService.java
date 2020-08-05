package me.kingcjy.webshop.sale.application;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.model.Money;
import me.kingcjy.webshop.sale.domain.Item;
import me.kingcjy.webshop.sale.domain.SaleItem;
import me.kingcjy.webshop.sale.domain.SaleItemRepository;
import me.kingcjy.webshop.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author KingCjy
 */
@Service
@RequiredArgsConstructor
public class SaleItemRegistrationService {

    private final SaleItemRepository saleItemRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long registrationInPlugin(SaleItemDto.SaleItemRequest saleItemRequest) {
        Long sellerId = userRepository.findUserIdFromUUID(saleItemRequest.getUuid());

        SaleItem saleItem = new SaleItem(
                saleItemRequest.getServerId(),
                sellerId,
                new Item(saleItemRequest.getName(), saleItemRequest.getDescription(), saleItemRequest.getImage(), saleItemRequest.getItem()),
                new Money(saleItemRequest.getPrice()),
                saleItemRequest.getQuantity());

        saleItemRepository.save(saleItem);
        return saleItem.getId();
    }

    public Long registrationInWeb(SaleItemDto.SaleItemRequest saleItemRequest) {
//        TODO
        return 1L;
    }
}
