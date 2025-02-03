package com.semzy_ecommerce.soft.dtos.response;

import java.util.ArrayList;
import java.util.List;

public class OrdereditemsResponse {

    private  int orderId;

    private String status;

    List<MyItems> products = new ArrayList<>();

    private Double totalAmount;

    public OrdereditemsResponse() {
    }

    public int getOrderId() {
        return orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public  void addItem(MyItems myItems){
        products.add(myItems);
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MyItems> getProducts() {
        return products;
    }

    public void setProducts(List<MyItems> products) {
        this.products = products;
    }
}
