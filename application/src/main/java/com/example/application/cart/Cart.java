package com.example.application.cart;

import com.example.application.product.Product;
import com.example.application.product.ProductRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<CartItem> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }

    public double getTotalPrice(List<Product> products) {
        return items.stream().mapToDouble(item -> {
            Product product = products.stream()
                    .filter(p -> p.getId().equals(item.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            return product.getPrice() * item.getQuantity();
        }).sum();
    }

    public void emptyCart() {
        items.clear();
    }

    public List<CartItem> getAllItems() {
        return new ArrayList<>(items);
    }
}