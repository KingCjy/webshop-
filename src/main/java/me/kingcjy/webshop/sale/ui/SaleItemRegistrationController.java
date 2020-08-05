package me.kingcjy.webshop.sale.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.model.ReturnId;
import me.kingcjy.webshop.common.security.SecurityUser;
import me.kingcjy.webshop.config.annotation.ServerId;
import me.kingcjy.webshop.sale.application.SaleItemDto;
import me.kingcjy.webshop.sale.application.SaleItemRegistrationService;
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
@RequiredArgsConstructor
public class SaleItemRegistrationController {

    private final SaleItemRegistrationService saleItemRegistrationService;

    @PostMapping("/plugin/api/v1/sale")
    public ResponseEntity<ReturnId> registrationInPlugin(
            @ServerId Long serverId,
            @RequestBody @Valid SaleItemDto.SaleItemRequest saleItemRequest) {
        saleItemRequest.setServerId(serverId);
        Long id = saleItemRegistrationService.registrationInPlugin(saleItemRequest);
        return Response.created(ReturnId.from(id), "");
    }

    @PostMapping("/api/v1/sale")
    public ResponseEntity<ReturnId> registrationInWeb(
            SecurityUser securityUser,
            @RequestBody @Valid SaleItemDto.SaleItemRequest saleItemRequest) {
        Long id = saleItemRegistrationService.registrationInWeb(saleItemRequest);
        return Response.created(ReturnId.from(id), "");
    }
}
