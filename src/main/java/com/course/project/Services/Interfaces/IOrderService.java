package com.course.project.Services.Interfaces;

import com.course.project.Models.Order;

import java.util.List;

public interface IOrderService {
    Order Get(long id);
    List<Order> GetAll();
    Boolean Save(Order order);
    Boolean Delete(long id);
}
