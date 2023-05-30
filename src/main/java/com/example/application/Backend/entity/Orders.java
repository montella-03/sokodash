package com.example.application.Backend.entity;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "orders")
public class Orders {
    @Id
    private Long orderId;
    private Long customerId;
    private Instant orderDate;
    @OneToOne
    private Product products;
    private Double amount;

    
}
