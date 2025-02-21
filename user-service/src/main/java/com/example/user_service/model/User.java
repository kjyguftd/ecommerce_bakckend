package com.example.user_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user") // 指定数据库表名
@Data // 自动生成 getter、setter、toString、equals 和 hashCode
@NoArgsConstructor // 生成无参构造函数
@AllArgsConstructor // 生成全参构造函数
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", length = 254)
    private String email;

    @Column(name = "role", nullable = false) //
    private Integer role;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

}