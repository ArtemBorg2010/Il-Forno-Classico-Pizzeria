package com.example.Il.Forno.Classico.Pizzeria.repos;

import com.example.Il.Forno.Classico.Pizzeria.entity.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс для получения данных из таблицы orders
 */
@Repository
public interface OrderRepository extends CrudRepository<Orders, String> {
    List<Orders> findAll();

    Orders findOrdersById(Long id);

    List<Orders> findOrdersByActiveIsTrue();
}
