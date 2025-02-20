package com.example.application.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    // 根据 userId 查询所有购物车
    List<CartItem> findAllByUserId(Long userId);

    // 是否存在userId
    boolean existsByUserId(Long userId);

}
