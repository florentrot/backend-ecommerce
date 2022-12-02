package com.florentin.ecomerce.service;

import com.florentin.ecomerce.dto.Purchase;
import com.florentin.ecomerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

}
