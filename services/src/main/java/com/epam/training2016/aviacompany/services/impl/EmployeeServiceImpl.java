package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.EmployeeDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {
	@Inject
	private EmployeeDaoImpl employeeDao;



	@Override
	public EmployeeWithJobtitle getWithJobtitle(Long id) {
		return employeeDao.getWithJobtitle(id);
	}


	@Override
	public List<Employee> getByJobTitleName(String name) {
		List<Employee> resultList = new ArrayList<Employee>();
		for(EmployeeWithJobtitle emp: employeeDao.getAllWithJobtitle()) {
			if (emp.getJobtitle().getName().indexOf(name) != -1) {
				resultList.add(emp.getEmployee());
			}
		}
		return resultList;
	}

	
	@Override
	@Transactional
	public void deleteByJobTitleId(Long jobtitleId) {
		for(Employee emp: employeeDao.getByJobTitleId(jobtitleId)) {
			this.deleteById(emp.getId());
		}
		
	}

}
