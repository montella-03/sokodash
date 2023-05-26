package com.example.application.Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.Backend.Repository.CustomerRepository;
import com.example.application.Backend.Repository.OrderRepository;
import com.example.application.Backend.Repository.ProductRepository;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    
}
