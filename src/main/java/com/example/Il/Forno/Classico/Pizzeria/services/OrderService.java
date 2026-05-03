package com.example.Il.Forno.Classico.Pizzeria.services;

import com.example.Il.Forno.Classico.Pizzeria.entity.Orders;

import java.util.List;

/**
 * Интерфейс с перечислением методов для работы с функциональностью заказа
 */
public interface OrderService {
    /**
     * Метод сохраняет заказ в базе данных (таблица orders)
     *
     * @param order заказ
     */
    void saveOrder(Orders order);

    /**
     * Метод возвращает список активных заказов
     *
     * @return список активных заказов
     */
    List<Orders> findOrdersByActiveIsTrue();

    /**
     * Метод делает заказ неактивным (заказ выполнен)
     *
     * @param id id заказа
     */
    void makeInactive(Long id);

    /**
     * Метод возвращает заказ, найденный по id
     *
     * @param id id заказа
     * @return заказ, найденный по id
     */
    Orders findOrdersById(Long id);
}
