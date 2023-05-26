package com.example.application.Backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.Backend.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long>{
    
}
