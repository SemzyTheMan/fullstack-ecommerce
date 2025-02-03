package com.semzy_ecommerce.soft.dao;

import com.semzy_ecommerce.soft.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository  extends JpaRepository<CartItem,Integer> {
}
