package com.example.Il.Forno.Classico.Pizzeria.services;

import com.example.Il.Forno.Classico.Pizzeria.entity.Employee;
import com.example.Il.Forno.Classico.Pizzeria.entity.Orders;

import java.util.List;

/**
 * Интерфейс с перечислением методов для работы с функциональностью работника
 */
public interface EmployeeService {
    /**
     * Метод возвращает работника
     *
     * @return работник
     */
    Employee getEmployee();

    /**
     * Метод добавляет заказ в список выполненных им заказов
     *
     * @param order заказ
     */
    void addOrder(Orders order);

    /**
     * Метод возвращает список выполненных работником заказов
     *
     * @return список выполненных работником заказов
     */
    List<Orders> getOrders();

    /**
     * Метод авторизует работника (работник входит в аккаунт)
     *
     * @param nickname логин
     * @param password пароль
     * @return true если авторизация прошла успешно и false в противном случае
     */
    boolean login(String nickname, String password);

    /**
     * Метод регистрирует работника (работник создает новый аккаунт)
     *
     * @param name     имя
     * @param position должность
     * @param nickname логин
     * @param password пароль
     * @return true если авторизация прошла успешно и false в противном случае
     */
    boolean register(String name, String position, String nickname, String password);
}
