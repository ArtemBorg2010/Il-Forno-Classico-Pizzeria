package com.example.Il.Forno.Classico.Pizzeria.repos;

import com.example.Il.Forno.Classico.Pizzeria.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
}
