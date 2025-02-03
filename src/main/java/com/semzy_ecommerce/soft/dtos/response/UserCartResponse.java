package com.semzy_ecommerce.soft.dtos.response;

public class UserCartResponse {
    private  int productId;
    private String productName;
    private int quantity;
    private double productPrice;
    private String imageUrl;

    public UserCartResponse(int productId, int quantity, String productName, double productPrice, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
