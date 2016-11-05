package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.EmployeeDaoImpl;
import com.epam.training2016.aviacompany.daodb.impl.Flight2EmployeeDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.training2016.aviacompany.services.Flight2EmployeeService;
import com.epam.training2016.aviacompany.services.JobTitleService;
import com.epam.training2016.aviacompany.services.utils.IdNullException;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Inject
	private EmployeeDaoImpl employeeDao;
	@Inject
	private Flight2EmployeeService flight2EmployeeService;
	@Inject
	private JobTitleService jobtitleService;

	@Override
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
	public EmployeeWithJobtitle getWithJobtitle(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
		return employeeDao.getWithJobtitle(id);
	}

	@Override
	public List<Employee> getAll() {
		return employeeDao.getAll();
	}


	@Override
	public Employee getById(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
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
	public void deleteById(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
		String empString = getById(id).toString();
		employeeDao.deleteById(id);
		LOGGER.info(String.format("Deleted (%s) from table Employee", empString));
	}
	
	@Override
	@Transactional
	public void deleteCascadeById(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
		flight2EmployeeService.deleteByEmployeeId(id);
		deleteById(id);
	}

	@Override
	public void deleteByJobTitleId(Long jobtitleId) throws IdNullException {
		IdNullException.CheckIdParameter(jobtitleId);
		for(Employee emp: employeeDao.getByJobTitleId(jobtitleId)) {
			deleteCascadeById(emp.getId());
		}
		
	}

	@Override
	public List<Employee> filter(Employee entityFilter) {
		List<Employee> resultList = new ArrayList<Employee>(); 
		for(Employee employee: employeeDao.getAll()) {
			if (employee.filter(entityFilter)) {
				resultList.add(employee);
			}
		}
		return resultList;
	}


}
