package com.course.project.Services.Impl;

import com.course.project.Models.Order;
import com.course.project.Repositories.OrderRepository;
import com.course.project.Services.Interfaces.IOrderService;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order Get(long id) {
        return orderRepository.findById(id).orElseThrow();
    }
    @Override
    public List<Order> GetAll() {
        return orderRepository.findAll();
    }
    public List<Order> GetByUser(long id){
        return orderRepository.findByUserId(id);
    }

    @Override
    public Boolean Save(Order order) {
        try{
            orderRepository.save(order);
            return true;
        }catch(Exception ex){
            return  false;
        }
    }

    @Override
    public Boolean Delete(long id) {
        try{
            orderRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return  false;
        }
    }
}
