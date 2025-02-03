package com.semzy_ecommerce.soft.service;

import com.semzy_ecommerce.soft.dao.CategoryRepository;
import com.semzy_ecommerce.soft.dtos.CategoryResponse;
import com.semzy_ecommerce.soft.entity.Category;
import com.semzy_ecommerce.soft.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<CategoryResponse> getAllCategory() {
        List<Category> all = categoryRepository.findAll();
        return all.stream().map(c -> new CategoryResponse(c.getId(), c.getName(),c.getImageUrl()))
                .toList();
    }

    public Category getAllCategoryProducts(int id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    public Category addCategoryProducts(int id, List<Integer> productsIds) {
        Category tempCategory = categoryRepository.findById(id);
        List<Product> tempProducts = new ArrayList<>();
        productsIds.stream().
                map(i -> productService.getProductById(i)).
                forEach(p -> tempProducts.add(p));
        tempCategory.setProducts(tempProducts);
        return categoryRepository.save(tempCategory);
    }

    public Category getProductsByCategoryId(int id){
       return categoryRepository.findById(id);
    }
}
