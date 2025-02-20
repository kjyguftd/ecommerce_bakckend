package com.example.application.cart;

import com.example.application.product.Product;
import com.example.application.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.application.cart.CartItem;
import com.example.application.cart.CartRepository;
import com.example.application.cart.CartService;

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
    void getAllItemsByUserIdTest()
    {
        CartItem cartItem1 = new CartItem(1L,900L,300L,2,1);
        CartItem cartItem2 = new CartItem(2L,900L,301L,2,1);
        CartItem cartItem3 = new CartItem(3L,901L,301L,3,1);
        CartItem cartItem4 = new CartItem(4L,901L,303L,10,1);

        Product product1 = new Product(300L, "Product 1", 10.0);
        Product product2 = new Product(3001L, "Product 2", 20.0);
        Product product3 = new Product(3003L, "Product 2", 20.0);

        when(productRepository.findById(300L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(301L)).thenReturn(Optional.of(product2));
        when(productRepository.findById(303L)).thenReturn(Optional.of(product3));

        when(cartRepository.findAllByUserId(900L)).thenReturn(Arrays.asList(cartItem1, cartItem2));
        when(cartRepository.findAllByUserId(900L)).thenReturn(Arrays.asList(cartItem3, cartItem4));

        double totalPrice1 = cartService.calculateTotalPrice(900L);
        double totalPrice2 = cartService.calculateTotalPrice(901L);

        assertEquals(60.0, totalPrice1);
        assertEquals(260.0, totalPrice2);
    }

}