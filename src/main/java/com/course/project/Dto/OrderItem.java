package com.course.project.Dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class OrderItem {
    @JsonManagedReference
    private ProductRead product;
    private long quantity;

    public ProductRead getProduct() {
        return product;
    }

    public void setProduct(ProductRead product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
