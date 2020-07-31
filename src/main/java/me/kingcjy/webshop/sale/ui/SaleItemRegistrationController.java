package me.kingcjy.webshop.sale.ui;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.model.ReturnId;
import me.kingcjy.webshop.common.service.ImageService;
import me.kingcjy.webshop.sale.application.SaleItemDto;
import me.kingcjy.webshop.sale.application.SaleItemRegistrationService;
import me.kingcjy.webshop.util.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author KingCjy
 */
@RestController
@RequestMapping("/api/v1/sale/plugin")
@RequiredArgsConstructor
public class SaleItemRegistrationController {

    private final SaleItemRegistrationService saleItemRegistrationService;

    @PostMapping
    public Response<?> registration(@RequestBody @Valid SaleItemDto.SaleItemRequest saleItemRequest) {
        Long id = saleItemRegistrationService.registration(saleItemRequest);

        return Response.created(ReturnId.from(id), "");
    }
}
