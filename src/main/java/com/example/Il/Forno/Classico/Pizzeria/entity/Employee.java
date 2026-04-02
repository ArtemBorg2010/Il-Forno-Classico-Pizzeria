package com.example.Il.Forno.Classico.Pizzeria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранит информацию о работнике.
 */
@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String position;
    private String hireDate;
    private String pizzeriaLocation;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders=new ArrayList<>();

    public void addOrder(Orders order) {
        orders.add(order);
    }
}
