package com.example.Il.Forno.Classico.Pizzeria.repos;

import com.example.Il.Forno.Classico.Pizzeria.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    List<Product> findAll();

    Product findProductById(Long id);
}
