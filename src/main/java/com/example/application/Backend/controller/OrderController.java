package com.example.application.Backend.controller;

import com.example.application.Backend.model.OrderModel;
import com.example.application.Backend.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final StoreService storeService;

    public OrderController(StoreService storeService) {
        this.storeService = storeService;
    }
    @PostMapping
    public ResponseEntity<Void> addOrder(@RequestBody OrderModel orderModel){
        storeService.addOrder(orderModel);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
