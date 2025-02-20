package com.example.application.cart;

import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.application.response.ErrorResponse;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;

    @Autowired
    public CartController(CartService cartService, CartRepository cartRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }

    //根据用户Id获取购物车
    @GetMapping("/{userid}")
    public ResponseEntity<?> getCart(@PathVariable Long userid) {
        if(cartService.existUserId(userid)) {
            List<CartItem> items = cartService.getAllItemsByUserId(userid);
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
        else{
            // 处理异常并返回错误信息
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "UserId Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

        }
    }

    //加购一个商品
    @PostMapping("/additem/{userid}")
    public ResponseEntity<?> addItemToCart(@PathVariable Long userid, @RequestBody CartItem item) {
        if(!cartService.existUserId(userid)){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "UserId Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        try {
            cartService.addItem(userid, item);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR
                    .value(), "An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //勾选or取消勾选一个商品
    @PatchMapping("/checkitem/{id}/{check}")
    public ResponseEntity<?> CheckItem(@PathVariable Long id,@PathVariable int check)
    {
        if(!cartService.existCartItem(id))
        {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "CartItem Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        try{
            cartService.checkItem(id,check);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR
                    .value(), "An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //勾选or取消勾选全部商品
    @PatchMapping("/checkitems/{userid}/{check}")
    public ResponseEntity<?> CheckAllItem(@PathVariable Long userid,@PathVariable int check)
    {
        if(!cartService.existUserId(userid))
        {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        try{
            cartService.checkAllItems(userid,check);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR
                    .value(), "An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //移除一个商品
    @DeleteMapping("/removeitem/{id}")
    public ResponseEntity<?>  removeItem(@PathVariable Long id) {
        if(!cartService.existCartItem(id))
        {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "CartItem Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        try{
            cartService.removeItem(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR
                    .value(), "An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //移除所有商品
    @DeleteMapping("/removeitems/{userid}")
    public ResponseEntity<?>  removeAllItems(@PathVariable Long userid) {
        if(!cartService.existUserId(userid))
        {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        try{
            cartService.removeAllItems(userid);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR
                    .value(), "An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //计算购物车商品总价
    @GetMapping("/total/{userid}")
    public ResponseEntity<?>  getTotalPrice(@PathVariable Long userid) {
        if(!cartService.existUserId(userid))
        {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        try{
            double totalPrice = cartService.calculateTotalPrice(userid);
            return new ResponseEntity<>(totalPrice, HttpStatus.OK);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR
                    .value(), "An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //计算购物车商品总价
    @GetMapping("/checkedsum/{userid}")
    public ResponseEntity<?>  getCheckedPrice(@PathVariable Long userid) {
        if(!cartService.existUserId(userid))
        {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User Not Found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        try{
            double checkedPrice = cartService.calculateCheckedPrice(userid);
            return new ResponseEntity<>(checkedPrice, HttpStatus.OK);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR
                    .value(), "An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //todo:勾选商品下单

}
