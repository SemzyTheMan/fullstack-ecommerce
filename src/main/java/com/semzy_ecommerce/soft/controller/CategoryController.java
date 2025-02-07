package com.semzy_ecommerce.soft.controller;


import com.semzy_ecommerce.soft.dtos.CategoryResponse;
import com.semzy_ecommerce.soft.dtos.ProductsCategory;
import com.semzy_ecommerce.soft.dtos.requests.CategoryRequest;
import com.semzy_ecommerce.soft.entity.Category;
import com.semzy_ecommerce.soft.service.CategoryService;
import com.semzy_ecommerce.soft.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    CloudinaryService cloudinaryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> createCategory(@ModelAttribute CategoryRequest categoryRequest) {

        String imageUrl = cloudinaryService
                .uploadFile(categoryRequest.getCategoryImg(), "category_img");

        Category category = new Category(categoryRequest.getName(), imageUrl);
        Category tempCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(tempCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {

        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @PostMapping("/addProducts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addCategoryProducts(@PathVariable int id,
                                                        @RequestBody ProductsCategory productsCategory) {
        Category tempCategory = categoryService.addCategoryProducts(id, productsCategory.getProductIds());
        return new ResponseEntity<>(tempCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getProductsByCategory(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.getProductsByCategoryId(id), HttpStatus.ACCEPTED);
    }
}
