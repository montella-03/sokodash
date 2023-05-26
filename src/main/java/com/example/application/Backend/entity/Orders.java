package com.example.application.Backend.entity;

import java.time.Instant;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Orders {
    private Long orderId;
    private Long customerId;
    private Instant orderDate;
    @OneToMany
    private List<Product> products;
    private Double amount;

    
}
