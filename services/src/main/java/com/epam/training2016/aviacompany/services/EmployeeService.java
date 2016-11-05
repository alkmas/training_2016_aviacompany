package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.services.utils.IdNullException;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

public interface EmployeeService extends BaseService<Employee> {
	EmployeeWithJobtitle getWithJobtitle(Long flightId) throws IdNullException;
	List<Employee> getByJobTitleName(String nameJob);
	void deleteByJobTitleId(Long jobtitleId) throws IdNullException;
}
