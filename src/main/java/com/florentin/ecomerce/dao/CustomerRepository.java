package com.florentin.ecomerce.dao;

import com.florentin.ecomerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
