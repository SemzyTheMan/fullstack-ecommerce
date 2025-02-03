package com.semzy_ecommerce.soft.dtos;

public class BoxItem {
    private  int quantity;
    private String item;

    public BoxItem(int quantity, String item) {
        this.quantity = quantity;
        this.item = item;
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
