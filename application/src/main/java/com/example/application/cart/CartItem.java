package com.example.application.cart;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import java.util.Date;


@Entity
public class CartItem {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    private Long productId;
    private int quantity;
    private int checked;
    private Date createTime;
    private Date updateTime;

    public CartItem(){}

    public CartItem(Long id,Long userId, Long productId, int quantity, int checked) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}