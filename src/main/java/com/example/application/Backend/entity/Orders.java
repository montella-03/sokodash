package com.example.application.Backend.entity;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    private Long orderId;
    private Long customerId;
    private Instant orderDate;
    @OneToOne
    private Product products;
    private Double amount;

    
}
