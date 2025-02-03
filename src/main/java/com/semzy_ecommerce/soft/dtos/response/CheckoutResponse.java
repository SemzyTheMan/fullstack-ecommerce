package com.semzy_ecommerce.soft.dtos.response;


import java.util.List;

public class CheckoutResponse {

    private List<CheckoutItem> cartItems;

    private double total;

    private double serviceCharge;

    public CheckoutResponse(List<CheckoutItem> cartItems, double total, double serviceCharge) {
        this.cartItems = cartItems;
        this.total = total;
        this.serviceCharge = serviceCharge;
    }

    public List<CheckoutItem> getCheckoutItems() {
        return cartItems;
    }

    public void setCheckoutItems(List<CheckoutItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
}
