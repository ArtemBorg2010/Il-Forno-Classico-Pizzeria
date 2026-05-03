package com.example.Il.Forno.Classico.Pizzeria.services;

import com.example.Il.Forno.Classico.Pizzeria.entity.Product;

import java.util.List;

/**
 * Интерфейс с перечислением методов для работы с функциональностью продукта
 */
public interface ProductService {
    /**
     * Метод добавляет продукт в меню
     *
     * @param name     название
     * @param category категория
     * @param price    цена
     * @param cost     стоимость
     */
    void addProduct(String name, String category, double price, double cost);

    /**
     * Метод возвращает продукт, найденный по id
     *
     * @param productId id продукта
     * @return продукт, найденный по id
     */
    Product findProductById(Long productId);

    /**
     * Метод возвращает список всех продуктов
     *
     * @return список всех продуктов
     */
    List<Product> findAll();
}
