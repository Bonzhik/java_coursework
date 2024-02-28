package com.course.project.Dto;

public class OrderItem {
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
