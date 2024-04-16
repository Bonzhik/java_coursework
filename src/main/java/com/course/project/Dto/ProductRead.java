package com.course.project.Dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

public class ProductRead {
    private long id;
    private String title;
    private long quantity;
    private long price;
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    private String imageUrl;
    @JsonManagedReference
    private List<CategoryRead> categories = new ArrayList<>();;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public List<CategoryRead> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryRead> categories) {
        this.categories = categories;
    }
}
