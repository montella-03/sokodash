package com.example.application.Backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    

    public Customer() {
    }
    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }
    //setters and getters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name= name;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address= address;
    }
    //to string
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", address=" + address + "]";
    }
}
