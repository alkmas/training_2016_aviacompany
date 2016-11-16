package com.epam.training2016.aviacompany.services.impl;

import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.services.EmployeeService;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

	@Override
	public boolean haveJobTitle(Long id, Long jobtitleId) {
		return (getById(id).getJobTitleId() == jobtitleId);
	}

	
}
