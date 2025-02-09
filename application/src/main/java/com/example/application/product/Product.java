package com.example.application.product;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String picture;
    private String category;
    private int stock;

    public Product() {

    }

    public Product(Double price, Long id, String name, String description, String picture, String category, int stock) {
        this.price = price;
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.category = category;
        this.stock = stock;
    }

    public Product(long l, String s, double v) {
        this.id = l;
        this.name = s;
        this.price = v;
    }

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
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}