package com.semzy_ecommerce.soft.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    List<CartItem> cartItems;

    @Column(name = "total_amount")
    private double totalAmount = 0;

    @Column(name = "subtotal")
    private double subTotal = 0;

    @Column(name = "service_charge")
    private double serviceCharge = 0;

    @OneToOne(mappedBy = "cart")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart() {
    }



    public void addCartItems(CartItem item) {

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(item);
    }

    public void removeCartItems(CartItem item) {
        cartItems.remove(item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartItems=" + cartItems +
                ", totalAmount=" + totalAmount +
                ", subTotal=" + subTotal +
                ", serviceCharge=" + serviceCharge +
                ", user=" + user +
                '}';
    }
}