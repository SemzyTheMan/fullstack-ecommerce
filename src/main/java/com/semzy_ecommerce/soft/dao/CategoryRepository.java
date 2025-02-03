package com.semzy_ecommerce.soft.dao;

import com.semzy_ecommerce.soft.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findById(int id);
}
