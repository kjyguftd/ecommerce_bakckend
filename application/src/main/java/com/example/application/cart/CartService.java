package com.example.application.cart;

import com.example.application.product.Product;
import com.example.application.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartService(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public double calculateTotalPrice(Cart cart) {
        List<Product> products = productRepository.findAllById(
                cart.getItems().stream().map(CartItem::getProductId).toList()
        );
        return cart.getTotalPrice(products);
    }

    public List<CartItem> getAllItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getAllItems();
    }

    public Cart addItemToCart(Long cartId, CartItem item) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.addItem(item);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.removeItem(productId);
        return cartRepository.save(cart);
    }

    public void emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.emptyCart();
        cartRepository.save(cart);
    }
}