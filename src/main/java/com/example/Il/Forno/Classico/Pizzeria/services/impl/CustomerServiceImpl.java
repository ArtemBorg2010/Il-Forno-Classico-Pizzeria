package com.example.Il.Forno.Classico.Pizzeria.services.impl;

import com.example.Il.Forno.Classico.Pizzeria.entity.Customer;
import com.example.Il.Forno.Classico.Pizzeria.repos.CustomerRepository;
import com.example.Il.Forno.Classico.Pizzeria.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Класс, реализующий методы интерфейса CustomerService
 */

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer = new Customer();

    @Override
    public boolean register(String name, String phone, String nickname, String password) {
        customer.setName(name);
        customer.setPhone(phone);
        if (customerRepository.findCustomerByNicknameAndPassword(nickname, password) == null) {
            customer.setNickname(nickname);
            customer.setPassword(password);
        } else {
            return false; //error occurred
        }
        customerRepository.save(customer);
        return true; //successful login
    }

    @Override
    public boolean login(String nickname, String password) {
        Customer checkCustomer = customerRepository.findCustomerByNicknameAndPassword(nickname, password);
        if (checkCustomer != null) {
            customer = checkCustomer;
            return true; //successful login
        }
        return false; //error occurred
    }
}
