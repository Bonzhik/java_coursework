package com.course.project.Dto;

import com.course.project.Models.Status;
import com.course.project.Models.User;

import java.util.ArrayList;
import java.util.List;

public class OrderRead {
    private long id;
    private Status status;
    private User user;
    private List<OrderItem> products = new ArrayList<>();;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItem> products) {
        this.products = products;
    }
}
