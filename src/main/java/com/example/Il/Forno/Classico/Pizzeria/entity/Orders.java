package com.example.Il.Forno.Classico.Pizzeria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранит информацию о заказе.
 */
@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String date;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToMany
    @JoinTable(
            name = "order_products", // имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "order_id"), // колонка этой сущности
            inverseJoinColumns = @JoinColumn(name = "product_id") // колонка связанной сущности
    )
    private List<Product> products=new ArrayList<>();
    private boolean active;

    public void addProduct(Product product) {
        products.add(product);
    }
}
