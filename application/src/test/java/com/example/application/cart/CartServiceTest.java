package com.example.application.cart;

import com.example.application.product.Product;
import com.example.application.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateTotalPrice() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1L, 2));
        cart.addItem(new CartItem(2L, 1));

        Product product1 = new Product(1L, "Product 1", 10.0);
        Product product2 = new Product(2L, "Product 2", 20.0);

        when(productRepository.findAllById(anyList())).thenReturn(Arrays.asList(product1, product2));

        double totalPrice = cartService.calculateTotalPrice(cart);

        assertEquals(40.0, totalPrice);
    }

    @Test
    void getAllItems() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1L, 2));
        cart.addItem(new CartItem(2L, 1));

        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        List<CartItem> items = cartService.getAllItems(1L);

        assertEquals(2, items.size());
    }

    @Test
    void addItemToCart() {
        Cart cart = new Cart();
        CartItem newItem = new CartItem(1L, 2);

        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart updatedCart = cartService.addItemToCart(1L, newItem);

        assertEquals(1, updatedCart.getItems().size());
        assertEquals(newItem, updatedCart.getItems().get(0));
    }

    @Test
    void removeItemFromCart() {
        Cart cart = new Cart();
        CartItem item = new CartItem(1L, 2);
        cart.addItem(item);

        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart updatedCart = cartService.removeItemFromCart(1L, 1L);

        assertTrue(updatedCart.getItems().isEmpty());
    }

    @Test
    void emptyCart() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1L, 2));
        cart.addItem(new CartItem(2L, 1));

        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        cartService.emptyCart(1L);

        assertTrue(cart.getItems().isEmpty());
    }
}