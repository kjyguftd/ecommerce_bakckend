package com.example.application.order;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long userId;
    private Double totalAmount;
    private String address;
    private String contactNumber;
    private LocalDateTime orderDate;

    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long id) {
        this.orderId = id;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}