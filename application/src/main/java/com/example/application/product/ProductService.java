package com.example.application.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        // Update logic here
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPicture(product.getPicture());
            existingProduct.setStock(product.getStock());
            existingProduct.setCategoryId(product.getCategoryId());
            existingProduct.setSubtitle(product.getSubtitle());
            existingProduct.setSubPicture(product.getSubPicture());
            existingProduct.setStatus(product.getStatus());
            return productRepository.save(existingProduct);

        }).orElse(null);
        /*return product;*/
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }



    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Integer getProductStock(Long id) {
        return productRepository.findById(id).map(Product::getStock).orElse(null);
    }


    public Page<Product> findProductsByName(String name, Pageable pageable) {
        return productRepository.findByNameContaining(name, pageable);
    }


    public Page<Product> findProductsByPriceRange(Double minPrice, Double maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    }


    public Page<Product> findProductsByCategory(int categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable);
    }


    public Page<Product> findAvailableProducts(Pageable pageable) {
        return productRepository.findByStatus(1, pageable);
    }
}