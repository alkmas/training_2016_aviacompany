package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

public interface EmployeeService extends BaseService<Employee> {
	EmployeeWithJobtitle getWithJobtitle(Long flightId);
	List<Employee> getByJobTitleName(String nameJob);
	/**
	 * Получить сотрудников свободных от полетов на дату 
	 * @param nameJob
	 * @param dateFree
	 * @return
	 */
	List<Employee> getFree(String nameJob, Date dateFree);
}
