package com.example.user_service.repository;

import com.example.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    User getByUsername(String username);
    // 根据用户名查找用户
    Optional<User> findByUsername(String username);

    // 根据邮箱查找用户
    Optional<User> findByEmail(String email);

    // 自定义查询：根据用户名模糊查找用户
    @Query("SELECT u FROM User u WHERE u.username LIKE %:username%")
    List<User> findByUsernameContaining(@Param("username") String username);

    // 自定义查询：根据创建时间范围查找用户
    @Query("SELECT u FROM User u WHERE u.createTime BETWEEN :startTime AND :endTime")
    List<User> findByCreateTimeBetween(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}

