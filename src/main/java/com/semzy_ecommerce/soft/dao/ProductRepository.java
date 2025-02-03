package com.semzy_ecommerce.soft.dao;

import com.semzy_ecommerce.soft.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findById(int id);

}
