package com.semzy_ecommerce.soft.dtos;

import com.semzy_ecommerce.soft.entity.InsideBox;

import java.util.List;

public class ProductboxDTO {

    private int id;
    private List<InsideBox> items;

    public ProductboxDTO(int id, List<InsideBox> items) {
        this.id = id;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<InsideBox> getItems() {
        return items;
    }

    public void setItems(List<InsideBox> items) {
        this.items = items;
    }
}
