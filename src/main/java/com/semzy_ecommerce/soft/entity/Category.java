package com.semzy_ecommerce.soft.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name="image_url")
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<Product> products;


    public Category() {
    }

    public Category(String name,String imageUrl) {
        this.name = name;
        this.imageUrl=imageUrl;
    }

    public void addProduct(List<Product> tempProducts) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.addAll(tempProducts);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
