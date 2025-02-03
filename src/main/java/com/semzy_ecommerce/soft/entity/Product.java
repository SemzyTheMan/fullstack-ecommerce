package com.semzy_ecommerce.soft.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity_available")
    private int quantityAvailable;

    @Column(name = "features",length = 1000)
    private String features;

    @Column(name="main_image_url")
    private String mainImageUrl;

    @Column(name="image_one")
    private String imageOne;

    @Column(name="image_two")
    private String imageTwo;

    @Column(name="image_three")
    private String imageThree;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
     private List<InsideBox> inThebox;

    public Product() {
    }

    public Product(String name, String description, double price, int quantityAvailable,
                   String features, String mainImageUrl, String imageOne,
                   String imageTwo, String imageThree) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.features = features;
        this.mainImageUrl = mainImageUrl;
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
        this.imageThree = imageThree;

    }
    public void addProductBoxContent(List<InsideBox> insideBox){
        if(insideBox==null){
            inThebox= new ArrayList<>();
        }
        inThebox.addAll(insideBox);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getImageThree() {
        return imageThree;
    }

    public void setImageThree(String imageThree) {
        this.imageThree = imageThree;
    }

    public List<InsideBox> getInThebox() {
        return inThebox;
    }

    public void setInThebox(List<InsideBox> inThebox) {
        this.inThebox = inThebox;
    }
}
