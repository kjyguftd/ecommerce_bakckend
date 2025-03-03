package com.example.application.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/{id}/stock")
    public Integer getProductStock(@PathVariable Long id) {
        return productService.getProductStock(id);
    }


    @GetMapping
    public Page<Product> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }


    @GetMapping("/search")
    public Page<Product> searchProducts(@RequestParam String name, Pageable pageable) {
        return productService.findProductsByName(name, pageable);
    }


    @GetMapping("/search/price")
    public Page<Product> searchByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice, Pageable pageable) {
        return productService.findProductsByPriceRange(minPrice, maxPrice, pageable);
    }


    @GetMapping("/category/{categoryId}")
    public Page<Product> getProductsByCategory(@PathVariable int categoryId, Pageable pageable) {
        return productService.findProductsByCategory(categoryId, pageable);
    }



    @GetMapping("/available")
    public Page<Product> getAvailableProducts(Pageable pageable) {
        return productService.findAvailableProducts(pageable);
    }
}