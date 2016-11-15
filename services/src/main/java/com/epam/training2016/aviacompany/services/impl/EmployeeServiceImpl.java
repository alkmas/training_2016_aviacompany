package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.BaseDao;
import com.epam.training2016.aviacompany.daodb.EmployeeDao;
import com.epam.training2016.aviacompany.daodb.impl.EmployeeDaoImpl;
import com.epam.training2016.aviacompany.daodb.impl.JobTitleDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {
	@Inject
	private EmployeeDao employeeDao;
	@Inject
	private BaseDao<JobTitle> jobtitleDao;



	@Override
	public boolean haveJobTitle(Long id, Long jobtitleId) {
		return (getById(id).getJobTitleId() == jobtitleId);
	}

	
}
