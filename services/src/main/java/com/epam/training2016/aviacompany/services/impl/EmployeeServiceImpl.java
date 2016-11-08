package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.EmployeeDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Inject
	private EmployeeDaoImpl employeeDao;


	@Override
	@Transactional
	public void saveAll(List<Employee> entities) {
		for(Employee entity: entities) {
			save(entity);
		}
	}

	@Override
	public void save(Employee entity) {
		if (entity.getId() == null) {
			entity.setId(employeeDao.insert(entity));
		} else {
			employeeDao.update(entity);
		}
	}

	@Override
	public boolean isDaoExist() {
		return employeeDao != null;
	}

	@Override
	public EmployeeWithJobtitle getWithJobtitle(Long id) {
		return employeeDao.getWithJobtitle(id);
	}

	@Override
	public List<Employee> getAll() {
		return employeeDao.getAll();
	}


	@Override
	public Employee getById(Long id) {
		return employeeDao.getById(id);
	}

	@Override
	public Employee getByName(String name) {
		return employeeDao.getByName(name);
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
	public void deleteById(Long id) {
		String empString = getById(id).toString();
		employeeDao.deleteById(id);
		LOGGER.info(String.format("Deleted (%s) from table Employee", empString));
	}
	
	@Override
	@Transactional
	public void deleteByJobTitleId(Long jobtitleId) {
		for(Employee emp: employeeDao.getByJobTitleId(jobtitleId)) {
			this.deleteById(emp.getId());
		}
		
	}

}
