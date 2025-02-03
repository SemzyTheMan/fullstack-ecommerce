package com.semzy_ecommerce.soft.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_box_content")
public class InsideBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name="item")
    private  String item;

    public InsideBox() {
    }

    public InsideBox(int quantity, String item) {
        this.quantity = quantity;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
