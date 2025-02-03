package com.semzy_ecommerce.soft.dtos.requests;

import com.semzy_ecommerce.soft.dtos.BoxItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductRequest {


    private String name;

    private String description;

    private double price;

    private int quantityAvailable;

    private String features;

    private MultipartFile mainImageUrl;

    private MultipartFile imageOne;

    private MultipartFile imageTwo;

    private MultipartFile imageThree;

    private List<BoxItem> inThebox;

    public ProductRequest(String name, String description, double price,
                          int quantityAvailable,
                          String features, MultipartFile mainImageUrl, MultipartFile imageOne,
                          MultipartFile imageTwo,
                          MultipartFile imageThree, List<BoxItem> inThebox) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.features = features;
        this.mainImageUrl = mainImageUrl;
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
        this.imageThree = imageThree;
        this.inThebox = inThebox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public MultipartFile getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(MultipartFile mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public MultipartFile getImageOne() {
        return imageOne;
    }

    public void setImageOne(MultipartFile imageOne) {
        this.imageOne = imageOne;
    }

    public MultipartFile getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(MultipartFile imageTwo) {
        this.imageTwo = imageTwo;
    }

    public MultipartFile getImageThree() {
        return imageThree;
    }

    public void setImageThree(MultipartFile imageThree) {
        this.imageThree = imageThree;
    }

    public List<BoxItem> getInThebox() {
        return inThebox;
    }

    public void setInThebox(List<BoxItem> inThebox) {
        this.inThebox = inThebox;
    }
}
