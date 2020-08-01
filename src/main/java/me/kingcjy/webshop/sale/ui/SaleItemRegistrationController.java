package me.kingcjy.webshop.sale.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.model.ReturnId;
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
@RequestMapping("/plugin/api/v1/sale/plugin")
@RequiredArgsConstructor
public class SaleItemRegistrationController {

    private final SaleItemRegistrationService saleItemRegistrationService;

    @PostMapping
    public ResponseEntity<ReturnId> registration(@RequestBody @Valid SaleItemDto.SaleItemRequest saleItemRequest) {
        Long id = saleItemRegistrationService.registration(saleItemRequest);

        return Response.created(ReturnId.from(id), "");
    }
}
