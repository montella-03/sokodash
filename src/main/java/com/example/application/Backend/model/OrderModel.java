package com.example.application.Backend.model;

import java.util.List;

public record OrderModel(Long customerId,List<ProductModel> ProductModel,double amount) {
    
}
