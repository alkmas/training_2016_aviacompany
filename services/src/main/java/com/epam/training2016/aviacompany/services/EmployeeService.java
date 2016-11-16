package com.epam.training2016.aviacompany.services;

import com.epam.training2016.aviacompany.datamodel.Employee;

public interface EmployeeService extends BaseService<Employee>{
	boolean haveJobTitle(Long id, Long jobId);
}
