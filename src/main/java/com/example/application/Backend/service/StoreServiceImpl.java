package com.example.application.Backend.service;

import com.example.application.Backend.Repository.CustomerRepository;
import com.example.application.Backend.Repository.OrderRepository;
import com.example.application.Backend.Repository.ProductRepository;
import com.example.application.Backend.entity.Customer;
import com.example.application.Backend.entity.Orders;
import com.example.application.Backend.entity.Product;
import com.example.application.Backend.model.CustomerModel;
import com.example.application.Backend.model.Model;
import com.example.application.Backend.model.OrderModel;
import com.example.application.Backend.model.ProductModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StoreServiceImpl implements StoreService {

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    public StoreServiceImpl(CustomerRepository customerRepository,
                            ProductRepository productRepository,
                            OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    //product methods

    @Override
    public void addProduct(ProductModel productModel) {
        Product product = Product.builder()
        .productName(productModel.productName())
        .quantity(productModel.quantity())
        .price(productModel.price())
        .build();
        productRepository.save(product);
    }
    @Override
    public void updateProduct(ProductModel productModel, Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        if(!productModel.productName().isEmpty()){product.setProductName(productModel.productName());}
        product.setQuantity(productModel.quantity());
        product.setPrice(productModel.price());
        productRepository.save(product);

    }
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
//filtering products method
    @Override
    public List<Product> findAll(String search) {
        return productRepository.findAll().stream().filter((e)->{
            return e.getProductName().contains(search);
        }).collect(Collectors.toList());

    }

    //customer methods
    @Override
    public void updateCustomer(CustomerModel customerModel, Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        if(!customerModel.name().isEmpty()){customer.setName(customerModel.name());}
        if(!customerModel.email().isEmpty()){customer.setEmail(customerModel.email());}
        if(!customerModel.address().isEmpty()){customer.setAddress(customerModel.address());}
        customerRepository.save(customer);

    }

    @Override
    public void addCustomer(CustomerModel customerModel) {
       Customer customer = 
       new Customer(customerModel.name(), customerModel.email(), customerModel.address());
         customerRepository.save(customer);
        
    }
    @Override
    public List<Customer> getAll(String search) {
        return customerRepository.findAll().stream().filter((e)->{
            return e.getName().contains(search);
        }).collect(Collectors.toList());

    }
    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();

    }

    @Override
    public double currentUSD() {
        //create a method that retries the current USD currency against Ksh?
        //for now lets return a constant number
        return 136.34;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);

    }
    //order methods
    @Transactional
    @Override
    public Orders addOrder(OrderModel orderModel) {
        Model model= orderModel.models();
        //getting products details
        Product product = productRepository.findByProductName(model.productName()).orElseThrow(
            ()->new RuntimeException("Product unavailable")
        );
        Orders orders = Orders.builder()
        .customerId(orderModel.customerId())
        .orderDate(Instant.now())
        .products(new Product(product.getId(),product.getProductName(),
                checkQuantity(model.quantity(), product.getQuantity()),
                product.getPrice()))
        .amount(model.quantity()*product.getPrice())
        .build();
        //updating product quantity
        product.setQuantity(product.getQuantity()-model.quantity());
        productRepository.save(product);

        orderRepository.save(orders);
        return orders;
        
    }

    private int checkQuantity(int productQ,int stock){
     if(stock<productQ){
         throw new RuntimeException("Product out of stock");
     }
     return productQ;

    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
        
    }

    @Override
    public List<Orders> orders() {
        return orderRepository.findAll();
    }

    @Override
    public int countProducts() {
        return productRepository.findAll().size();
    }

    @Override
    public String getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        return customer.getName();
    }
}
