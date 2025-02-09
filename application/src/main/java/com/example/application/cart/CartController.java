package com.example.application.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;

    @Autowired
    public CartController(CartService cartService, CartRepository cartRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }

    @PostMapping
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @PostMapping("/{id}/items")
    public Cart addItemToCart(@PathVariable Long id, @RequestBody CartItem item) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.addItem(item);
        return cartRepository.save(cart);
    }

    @DeleteMapping("/{id}/items/{productId}")
    public Cart removeItemFromCart(@PathVariable Long id, @PathVariable Long productId) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.removeItem(productId);
        return cartRepository.save(cart);
    }

    @GetMapping("/{id}/total")
    public double getTotalPrice(@PathVariable Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cartService.calculateTotalPrice(cart);
    }

    @PostMapping("/{id}/empty")
    public Cart emptyCart(@PathVariable Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.emptyCart();
        return cartRepository.save(cart);
    }

    @GetMapping("/{id}/items")
    public List<CartItem> getAllItems(@PathVariable Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getAllItems();
    }
}
