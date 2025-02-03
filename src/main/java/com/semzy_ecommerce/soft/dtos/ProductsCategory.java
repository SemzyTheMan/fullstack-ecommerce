package com.semzy_ecommerce.soft.dtos;

import java.util.List;

public class ProductsCategory {
    private List<Integer> productIds;

    public ProductsCategory(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setVendorIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

}
