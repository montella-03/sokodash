package com.example.application.Backend.service;

import java.util.List;

import com.example.application.Backend.entity.Orders;
import com.example.application.Backend.entity.Product;
import com.example.application.Backend.model.CustomerModel;
import com.example.application.Backend.model.OrderModel;
import com.example.application.Backend.model.ProductModel;

public interface StoreService {
    //products
    void addProduct(ProductModel productModel);
    void updateProduct(ProductModel productModel,Long id);
    void deleteProduct(Long id);
    List<Product> findAll();
    List<Product> findAll(String search);
    Product findById(Long id);

    //orders
    void addOrder(OrderModel orderModel);
    void deleteOrder(Long id);
    List<Orders> orders();

    //customers
    void addCustomer(CustomerModel customerModel);
    void updateCustomer(CustomerModel customerModel,Long id);
    void deleteCustomer(Long id);
    List<CustomerModel> getAll();

    double currentUSD();
}
