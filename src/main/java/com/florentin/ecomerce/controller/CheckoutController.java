package com.florentin.ecomerce.controller;

import com.florentin.ecomerce.dto.Purchase;
import com.florentin.ecomerce.dto.PurchaseResponse;
import com.florentin.ecomerce.service.CheckoutService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){

            PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

            return purchaseResponse;
    }


}
