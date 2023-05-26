package com.example.application.Backend.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Email
    private String email;
    private String address;
    

    public Customer() {
    }
    public Customer(String name,String email, String address) {
        this.name = name;
        this.address = address;
        this.email = email;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email= email;
    }
    //to string
    @Override
    public String toString() {
        return "Customer [address=" + address + ", email=" + email + ", id=" + id + ", name=" + name + "]";
    }
}
