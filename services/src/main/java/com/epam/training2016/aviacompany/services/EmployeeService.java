package com.epam.training2016.aviacompany.services;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

public interface EmployeeService extends BaseService<Employee> {
	EmployeeWithJobtitle getWithJobtitle(Long id);

}
