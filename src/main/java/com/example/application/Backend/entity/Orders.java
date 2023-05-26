package com.example.application.Backend.entity;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Orders {
    @Id
    private Long orderId;
    private Long customerId;
    private Instant orderDate;
    private List<Product> products;
    private Double amount;

    
}
