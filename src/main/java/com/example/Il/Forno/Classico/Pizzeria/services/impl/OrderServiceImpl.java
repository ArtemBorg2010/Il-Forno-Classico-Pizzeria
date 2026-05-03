package com.example.Il.Forno.Classico.Pizzeria.services.impl;

import com.example.Il.Forno.Classico.Pizzeria.entity.Orders;
import com.example.Il.Forno.Classico.Pizzeria.repos.OrderRepository;
import com.example.Il.Forno.Classico.Pizzeria.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс, реализующий методы интерфейса OrderService
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(Orders order) {
        orderRepository.save(order);
    }

    @Override
    public List<Orders> findOrdersByActiveIsTrue() {
        return orderRepository.findOrdersByActiveIsTrue();
    }

    @Override
    public void makeInactive(Long id) {
        Orders order = orderRepository.findOrdersById(id);
        order.setActive(false);
        orderRepository.save(order);
    }

    @Override
    public Orders findOrdersById(Long id) {
        return orderRepository.findOrdersById(id);
    }
}
