package com.example.application.Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.Backend.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    
}
