package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

public interface EmployeeDao extends BaseDao<Employee> {
	EmployeeWithJobtitle getWithJobtitle(Long id);
	List<EmployeeWithJobtitle> getAllWithJobtitle();
	List<Employee> getByJobTitleId(Long jobtitleId);
}
