package com.example.Il.Forno.Classico.Pizzeria.repos;

import com.example.Il.Forno.Classico.Pizzeria.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс для получения данных из таблицы employee
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    Employee findEmployeeByNicknameAndPassword(String nickname, String password);
}
