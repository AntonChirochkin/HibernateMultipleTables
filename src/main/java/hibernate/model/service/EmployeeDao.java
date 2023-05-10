package hibernate.model.service;

import hibernate.model.Employee;

import java.util.List;

public interface EmployeeDao {
    // добавление сущности
    Integer add(Employee employee);
    // получение по id
    Employee getById(int id);
    // получение всех сотрудников
    List<Employee> getAllEmployee();
    // добавление сотрудника
    Employee updateEmployee(Employee employee);
    // удаление сотрудника
    void deleteEmployee(Employee employee);
}