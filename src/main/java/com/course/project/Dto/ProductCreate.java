package com.course.project.Dto;

public class ProductCreate {
    private String title;
    private int price;
    private int quantity;
    private long[] categories;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long[] getCategories() {
        return categories;
    }

    public void setCategories(long[] categories) {
        this.categories = categories;
    }
}
