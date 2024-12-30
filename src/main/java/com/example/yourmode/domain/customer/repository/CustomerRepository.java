package com.example.yourmode.domain.customer.repository;

import com.example.yourmode.domain.business.entity.Business;
import com.example.yourmode.domain.customer.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Boolean existsByPhone(String phone);
    Page<Customer> findByBusiness(Business business, Pageable pageable);
}
