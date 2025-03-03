package com.example.application.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Page<Product> findByNameContaining(String name, Pageable pageable);


    Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);


    Page<Product> findByCategoryId(int categoryId, Pageable pageable);


    Page<Product> findByStatus(int status, Pageable pageable);

}