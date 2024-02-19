package com.course.project.Dto;

public class OrderCreate {
    private long[][] productsInOrder;

    public long[][] getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(long[][] productsInOrder) {
        this.productsInOrder = productsInOrder;
    }
}
