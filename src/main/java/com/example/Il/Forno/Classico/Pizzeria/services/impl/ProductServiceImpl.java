package com.example.Il.Forno.Classico.Pizzeria.services.impl;

import com.example.Il.Forno.Classico.Pizzeria.entity.Product;
import com.example.Il.Forno.Classico.Pizzeria.repos.ProductRepository;
import com.example.Il.Forno.Classico.Pizzeria.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс, реализующий методы интерфейса ProductService
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(String name, String category, double price, double cost) {
        Product p = new Product();
        p.setName(name);
        p.setCategory(category);
        p.setPrice(price);
        p.setCost(cost);
        productRepository.save(p);
    }

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findProductById(productId);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
