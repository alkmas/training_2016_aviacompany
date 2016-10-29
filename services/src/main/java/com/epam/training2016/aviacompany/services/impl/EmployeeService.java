package com.epam.training2016.aviacompany.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daodb.impl.EmployeeDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.services.BaseService;

@Service
public class EmployeeService implements BaseService<Employee> {
	@Inject
	private EmployeeDaoImpl employeeDao;

	@Override
	public void saveAll(List<Employee> entities) {
		for(Employee entity: entities) {
			save(entity);
		}
	}

	@Override
	public void save(Employee entity) {
		if (entity.getId() == null) {
			employeeDao.insert(entity);
		} else {
			employeeDao.update(entity);
		}
	}

	@Override
	public boolean isDaoExist() {
		return employeeDao != null;
	}

	@Override
	public Employee get(Long id) {
		return employeeDao.get(id);
	}

	@Override
	public Long insert(Employee entity) {
		return employeeDao.insert(entity);
	}

	@Override
	public List<Employee> getAll() {
		return employeeDao.getAll();
	}

}
