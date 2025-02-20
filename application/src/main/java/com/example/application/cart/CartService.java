package com.example.application.cart;

import com.example.application.product.Product;
import com.example.application.product.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartService(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    //购物车中是否存在用户
    public boolean existUserId(Long userId)
    {
        return cartRepository.existsByUserId(userId);
    }


    //购物车中是否存在商品
    public boolean existCartItem(Long id)
    {
        return cartRepository.existsById(id);
    }

    //商品表中是否存在商品
    public boolean existProduct(Long product_id)
    {
        return productRepository.existsById(product_id);
    }

    //根据用户Id获取购物车
    public List<CartItem> getAllItemsByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    //加购一个商品
    public void addItem(Long userId,CartItem item) {
        List<CartItem> items = cartRepository.findAllByUserId(userId);
        items.add(item);
        cartRepository.save(item);
    }

    //勾选or取消勾选一个商品
    public void checkItem(Long id,int checked)
    {
        CartItem item = cartRepository.findById(id).get();
        item.setChecked(checked);
        cartRepository.save(item);
    }

    //勾选or取消勾选全部商品
    public void checkAllItems(Long userId,int checked)
    {
        List<CartItem> items = cartRepository.findAllByUserId(userId);
        items.forEach(item -> {item.setChecked(checked);});
        cartRepository.saveAll(items);
    }

    //移除一个商品
    public void removeItem(Long id)
    {
        cartRepository.deleteById(id);
    }

    //移除所有商品
    public void removeAllItems(Long userId)
    {
        List<CartItem> items = cartRepository.findAllByUserId(userId);
        cartRepository.deleteAll(items);
    }

    //计算购物车总价
    public double calculateTotalPrice(Long userId)
    {
        List<CartItem> items = cartRepository.findAllByUserId(userId);
        double sum = 0;
        for (CartItem item : items) {
            Long productId = item.getProductId();
            Optional<Product> product = productRepository.findById(productId);
            if(product.isPresent()){
                sum += product.get().getPrice() * item.getQuantity();
            }
        }
        return sum;
    }

    //计算已勾选商品总价
    public double calculateCheckedPrice(Long userId)
    {
        List<CartItem> items = cartRepository.findAllByUserId(userId);
        List<CartItem> checkedItems = items.stream().filter(p->p.getChecked()==1).toList();
        double sum = 0;
        for (CartItem item : checkedItems) {
            Long productId = item.getProductId();
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                sum += product.get().getPrice() * item.getQuantity();
            }
        }
        return sum;
    }

    //todo:勾选商品下单

}