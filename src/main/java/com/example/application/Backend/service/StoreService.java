package com.example.application.Backend.service;

import java.util.List;

import com.example.application.Backend.entity.Customer;
import com.example.application.Backend.entity.Orders;
import com.example.application.Backend.entity.Product;
import com.example.application.Backend.model.CustomerModel;
import com.example.application.Backend.model.OrderModel;
import com.example.application.Backend.model.ProductModel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;

public interface StoreService {
    //products
    void addProduct(ProductModel productModel);
    void updateProduct(ProductModel productModel,Long id);
    void deleteProduct(Long id);
    List<Product> findAll();
    List<Product> findAll(String search);
    Product findById(Long id);

    //orders
    Orders addOrder(OrderModel orderModel);
    void deleteOrder(Long id);
    List<Orders> orders();

    //customers
    void addCustomer(CustomerModel customerModel);
    void updateCustomer(CustomerModel customerModel,Long id);
    void deleteCustomer(Long id);
    List<Customer> getAll();
    List<Customer> getAll(String search);

    double currentUSD();

    int countProducts();

    String getCustomer(Long customerId);
}
