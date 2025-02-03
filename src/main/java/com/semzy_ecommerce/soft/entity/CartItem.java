package com.semzy_ecommerce.soft.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    public CartItem() {
    }

    public CartItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
