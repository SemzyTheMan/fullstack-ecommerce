package com.semzy_ecommerce.soft.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase_details")
public class PurchaseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "transactionId")
    private String transactionId;

    @Column(name = "status")
    private String status;

    @Column(name = "amount",nullable = true)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_details_id")
    private List<PurchasedItem> purchasedItems;

    public PurchaseDetails() {
    }

    public PurchaseDetails(String transactionId, String status,double amount) {
        this.transactionId = transactionId;
        this.status = status;
        this.amount=amount;
    }

    public void addPurchasedItem(PurchasedItem item) {
        if (purchasedItems == null) {
            purchasedItems = new ArrayList<>();
        }
        purchasedItems.add(item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PurchasedItem> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<PurchasedItem> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }
}
