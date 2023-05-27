package com.example.application.Backend.entity;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    @ManyToMany
    private List<Product> products;
    private Double amount;

    
}
