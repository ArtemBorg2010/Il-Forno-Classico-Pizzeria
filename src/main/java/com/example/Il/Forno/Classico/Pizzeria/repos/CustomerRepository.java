package com.example.Il.Forno.Classico.Pizzeria.repos;

import com.example.Il.Forno.Classico.Pizzeria.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс для получения данных из таблицы customer
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    Customer findCustomerByNicknameAndPassword(String nickname, String password);
}
