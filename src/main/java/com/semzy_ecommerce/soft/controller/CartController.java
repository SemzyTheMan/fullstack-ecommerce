package com.semzy_ecommerce.soft.controller;

import com.semzy_ecommerce.soft.dtos.response.CheckoutResponse;
import com.semzy_ecommerce.soft.dtos.response.UserCartResponse;
import com.semzy_ecommerce.soft.entity.CartItem;
import com.semzy_ecommerce.soft.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addItem")
    public ResponseEntity<?> addItemToCart(@RequestParam int userId, @RequestBody List<CartItem> items) {
        cartService.addItemToCart(userId, items);

        return new ResponseEntity<>("Cart updated Successfully", HttpStatus.OK);
    }

    @PostMapping("/removeItem")
    public ResponseEntity<?> removeItemFromCart(@RequestParam int userId, @RequestParam int productId) {
        cartService.removeItemsFromCart(userId,productId);
        return new ResponseEntity<>("Cart updated Successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserCartResponse>> getUserCart(@RequestParam int userId
    ) {
        List<UserCartResponse> response = cartService.getProductsInCart(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/checkout")
    public ResponseEntity<CheckoutResponse> checkOutCart(@RequestParam int userId
    ) {
        CheckoutResponse response = cartService.checkOutCart(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
