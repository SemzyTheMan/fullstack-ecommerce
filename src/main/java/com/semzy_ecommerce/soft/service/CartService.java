package com.semzy_ecommerce.soft.service;

import com.semzy_ecommerce.soft.dao.CartItemRepository;
import com.semzy_ecommerce.soft.dao.CartRepository;
import com.semzy_ecommerce.soft.dao.ProductRepository;
import com.semzy_ecommerce.soft.dao.UserRepository;
import com.semzy_ecommerce.soft.dtos.response.CheckoutItem;
import com.semzy_ecommerce.soft.dtos.response.CheckoutResponse;
import com.semzy_ecommerce.soft.dtos.response.UserCartResponse;
import com.semzy_ecommerce.soft.entity.Cart;
import com.semzy_ecommerce.soft.entity.CartItem;
import com.semzy_ecommerce.soft.entity.Product;
import com.semzy_ecommerce.soft.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void addItemToCart(int userId, CartItem cartItem) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Cart existingUserCart = user.getCart();

        if (existingUserCart == null) {
            existingUserCart = new Cart();
            existingUserCart.setCartItems(new ArrayList<>());
        }

        // Create a new CartItem or update existing one
        boolean productExists = false;
        List<CartItem> items = existingUserCart.getCartItems();
        CartItem existingItem = null;

        // Find existing item first
        for (CartItem item : items) {
            if (item.getProductId() == cartItem.getProductId()) {
                existingItem = item;
                productExists = true;
                break;
            }
        }

        // Update or add outside the loop
        if (productExists) {
            items.remove(existingItem);
            cartItemRepository.deleteById(existingItem.getId());
            items.add(cartItem);
        } else {
            items.add(cartItem);
        }

        user.setCart(existingUserCart);
        userRepository.save(user);
    }

    @Transactional
    public void removeItemsFromCart(int userId, int productId) {

        User user = userRepository.findById(userId).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Cart existingUserCart = user.getCart();
        for (CartItem item : existingUserCart.getCartItems()) {
            if (item.getProductId() == productId) {
                existingUserCart.removeCartItems(item);
                cartItemRepository.delete(item);
                break;
            }
        }


        user.setCart(existingUserCart);
        userRepository.save(user);

    }

    public List<UserCartResponse> getProductsInCart(int userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<CartItem> cartItems = user.getCart().getCartItems();
        List<UserCartResponse> cartProducts = new ArrayList<>();

        for (CartItem item : cartItems) {
            Product tempProduct = productRepository.findById(item.getProductId());
            cartProducts.add(new UserCartResponse(tempProduct.getId(),
                    item.getQuantity(),
                    tempProduct.getName(),
                    tempProduct.getPrice(),
                    tempProduct.getMainImageUrl()
            ));
        }
        return cartProducts;
    }

    public CheckoutResponse checkOutCart(int userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Cart tempCart = user.getCart();
        List<CartItem> tempItems = user.getCart().getCartItems();
        List<CheckoutItem> checkoutItems = new ArrayList<>();
        double total = 0.0;

        for (CartItem item : tempItems) {

            Product product = productRepository.findById(item.getProductId());
            checkoutItems.add(new CheckoutItem(product.getName(), item.getQuantity()
                    , product.getMainImageUrl(), product.getPrice()));
            double myTotal = item.getQuantity() * product.getPrice();
            total = total + myTotal;
        }
        double serviceCharge = 0.05 * total;

        tempCart.setServiceCharge(serviceCharge);
        tempCart.setSubTotal(total);
        tempCart.setTotalAmount(serviceCharge + total);
        user.setCart(tempCart);
        userRepository.save(user);

        return new CheckoutResponse(checkoutItems, total, serviceCharge);

    }
}
