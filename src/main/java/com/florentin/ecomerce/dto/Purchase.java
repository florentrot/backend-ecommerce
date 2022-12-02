package com.florentin.ecomerce.dto;

import com.florentin.ecomerce.entity.Address;
import com.florentin.ecomerce.entity.Customer;
import com.florentin.ecomerce.entity.Order;
import com.florentin.ecomerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
