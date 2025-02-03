package com.semzy_ecommerce.soft.dtos.requests;

import org.springframework.web.multipart.MultipartFile;

public class CategoryRequest {

    private String name;

    private MultipartFile categoryImg;

    public CategoryRequest(String name, MultipartFile categoryImg) {
        this.name = name;
        this.categoryImg = categoryImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(MultipartFile categoryImg) {
        this.categoryImg = categoryImg;
    }
}
