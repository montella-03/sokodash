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
    public List<ProductModel> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((e)->{
            return new ProductModel(e.getProductName(),e.getQuantity(),e.getPrice());
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
    public List<CustomerModel> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map((e)->{
            return new CustomerModel(e.getName(),e.getEmail(),e.getAddress());
        }).collect(Collectors.toList());
    }
    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);

    }
    //order methods
    @Transactional
    @Override
    public void addOrder(OrderModel orderModel) {
        List<Model> productModels = orderModel.models();
        List<Product> list = productModels.stream().map((e)->{
            Product product= Product.builder()
            .productName(e.productName())
            .quantity(checkQuantity(e.quantity(),e.productName()))
            .price(getPrice(e.productName()))
            .build();
            return product;
             }).collect(Collectors.toList());

        Orders orders = Orders.builder()
        .customerId(orderModel.customerId())
        .orderDate(Instant.now())
        .products(list)
        .build();
        double total;
        total = 0.0;
        for(Product t:list){
                    total += t.getPrice()*t.getQuantity();
             }
                orders.setAmount(total);
        orderRepository.save(orders);
        
    }

    private int checkQuantity(int quantity,String name) {
        Product c = productRepository.findByProductName(name).orElseThrow(
            ()->new RuntimeException("Product  unavailable")
        );

        if(quantity<0||quantity>c.getQuantity()){
            throw new RuntimeException("Quantity cannot be negative");
        }
        c.setQuantity(c.getQuantity()-quantity);
        productRepository.save(c);
        return quantity;
    }

    private Double getPrice(String name) {
    
        Product c = productRepository.findByProductName(name).orElseThrow(
            ()->new RuntimeException("Product price unavailable")
        );
        return c.getPrice();
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
        
    }

    @Override
    public List<Orders> orders() {
        return orderRepository.findAll();
    }

}
