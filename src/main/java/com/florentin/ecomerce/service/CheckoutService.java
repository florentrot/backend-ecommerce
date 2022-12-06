package com.florentin.ecomerce.service;

import com.florentin.ecomerce.dto.PaymentInfo;
import com.florentin.ecomerce.dto.Purchase;
import com.florentin.ecomerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

}
