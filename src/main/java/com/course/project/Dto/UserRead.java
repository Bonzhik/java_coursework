package com.course.project.Dto;

import com.course.project.Models.Role;

import java.util.ArrayList;
import java.util.List;

public class UserRead {
    private long id;
    private String email;
    private String password;
    private Role role;
    private List<OrderRead> orders = new ArrayList<>();;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<OrderRead> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderRead> orders) {
        this.orders = orders;
    }
}
