package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Employee;

public interface EmployeeDao {
	Employee get(Long id);
    void insert(Employee entity);
    void update(Employee entity);
    void delete(Long id);
    List<Employee> getAll();
}
