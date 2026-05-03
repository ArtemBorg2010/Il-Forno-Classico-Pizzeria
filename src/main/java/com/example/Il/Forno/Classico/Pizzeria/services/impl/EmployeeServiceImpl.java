package com.example.Il.Forno.Classico.Pizzeria.services.impl;

import com.example.Il.Forno.Classico.Pizzeria.entity.Employee;
import com.example.Il.Forno.Classico.Pizzeria.entity.Orders;
import com.example.Il.Forno.Classico.Pizzeria.repos.EmployeeRepository;
import com.example.Il.Forno.Classico.Pizzeria.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс, реализующий методы интерфейса EmployeeService
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee = new Employee();

    @Override
    public Employee getEmployee() {
        return employee;
    }

    @Override
    public void addOrder(Orders order) {
        employee.addOrder(order);
    }

    @Override
    public List<Orders> getOrders() {
        return employee.getOrders();
    }

    @Override
    public boolean login(String nickname, String password) {
        Employee checkEmployee = employeeRepository.findEmployeeByNicknameAndPassword(nickname, password);
        if (checkEmployee != null) {
            employee = checkEmployee;
            return true; //successful login
        }
        return false; //error occurred
    }

    @Override
    public boolean register(String name, String position, String nickname, String password) {
        employee.setName(name);
        employee.setPosition(position);
        if (employeeRepository.findEmployeeByNicknameAndPassword(nickname, password) == null) {
            employee.setNickname(nickname);
            employee.setPassword(password);
        } else {
            return false; //error occurred
        }
        employeeRepository.save(employee);
        return true; //successful login
    }
}
