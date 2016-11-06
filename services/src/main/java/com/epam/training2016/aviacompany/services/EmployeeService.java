package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

public interface EmployeeService {
    void saveAll(List<Employee> entities);
    void save(Employee entity);
    void deleteById(Long id);
    boolean isDaoExist();
    Employee getById(Long id);
    Employee getByName(String name);
    List<Employee> getAll();
    List<Employee> filter(Employee entityFilter);
	EmployeeWithJobtitle getWithJobtitle(Long id);
	List<Employee> getByJobTitleName(String nameJob);
	void deleteByJobTitleId(Long jobtitleId);
}
