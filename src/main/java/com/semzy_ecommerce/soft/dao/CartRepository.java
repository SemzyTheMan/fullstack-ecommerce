package com.semzy_ecommerce.soft.dao;

import com.semzy_ecommerce.soft.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findById(int id);
}
