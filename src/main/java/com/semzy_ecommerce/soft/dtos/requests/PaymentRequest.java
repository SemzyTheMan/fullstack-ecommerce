package com.semzy_ecommerce.soft.dtos.requests;

public class PaymentRequest {

    private String tx_ref;

    private double amount;
    private final String currency = "NGN";
    private String redirect_url;
    private Customer customer;

    public PaymentRequest() {
    }

    public PaymentRequest(String tx_ref, double amount, String redirect_url, Customer customer) {
        this.tx_ref = tx_ref;
        this.amount = amount;
        this.redirect_url = redirect_url;
        this.customer = customer;
    }

    public String getTx_ref() {
        return tx_ref;
    }

    public void setTx_ref(String tx_ref) {
        this.tx_ref = tx_ref;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "tx_ref='" + tx_ref + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", redirect_url='" + redirect_url + '\'' +
                ", customer=" + customer +
                '}';
    }
}
