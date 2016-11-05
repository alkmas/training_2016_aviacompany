package com.epam.training2016.aviacompany.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.Flight2EmployeeDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;
import com.epam.training2016.aviacompany.services.Flight2EmployeeService;
import com.epam.training2016.aviacompany.services.utils.IdNullException;

@Service
public class Flight2EmployeeServiceImpl implements Flight2EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Flight2EmployeeServiceImpl.class);

	@Inject
	private Flight2EmployeeDaoImpl flight2EmployeeDao;

	@Override
	public void saveAll(List<Flight2Employee> entities) {
		for(Flight2Employee entity: entities) {
			save(entity);
		}
	}

	@Override
	public void save(Flight2Employee entity) {
		if (entity.getId() == null) {
			entity.setId(flight2EmployeeDao.insert(entity));
		} else {
			flight2EmployeeDao.update(entity);
		}
	}

	@Override
	public boolean isDaoExist() {
		return flight2EmployeeDao != null;
	}

	@Override
	public Flight2Employee getById(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
		return flight2EmployeeDao.getById(id);
	}

	@Override
	public List<Flight2Employee> getAll() {
		return flight2EmployeeDao.getAll();
	}

	@Override
	public Flight2Employee getByName(String name) {
		return flight2EmployeeDao.getByName(name);
	}

	@Override
	public List<Flight2Employee> getByFlightId(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
		return flight2EmployeeDao.getByFlightId(id);
	}

	@Override
	public List<Flight2Employee> getByDeparture(Date dt) {
		List<Flight2Employee> resultList = new ArrayList<Flight2Employee>();
		for(Flight2Employee f2e: flight2EmployeeDao.getAll()){
			if (f2e.getDeparture().getTime() == dt.getTime()) {
				resultList.add(f2e);
			}
		}
		return resultList;
	}

	@Override
	public List<Flight2Employee> filter(Flight2Employee entityFilter) {
		List<Flight2Employee> resultList = new ArrayList<Flight2Employee>(); 
		for(Flight2Employee f2e: flight2EmployeeDao.getAll()) {
			if (f2e.filter(entityFilter)) {
				resultList.add(f2e);
			}
		}
		return resultList;
	}

	@Override
	public void deleteById(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
		String f2eString = getById(id).toString();
		flight2EmployeeDao.deleteById(id);
		LOGGER.info(String.format("Deleted (%s) from table Flight2Employee", f2eString));
	}

	
	@Override
	public void deleteCascadeById(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
		deleteById(id);
	}

	@Override
	@Transactional
	public void deleteByEmployeeId(Long employeeId) throws IdNullException {
		IdNullException.CheckIdParameter(employeeId);
		for(Flight2Employee f2e: flight2EmployeeDao.getByEmployeeId(employeeId)) {
			deleteCascadeById(f2e.getId());
		}
	}

	@Override
	@Transactional
	public void deleteByFlightId(Long flightId) throws IdNullException {
		IdNullException.CheckIdParameter(flightId);
		for(Flight2Employee f2e: flight2EmployeeDao.getByFlightId(flightId)) {
			deleteCascadeById(f2e.getId());
		}
	}

}
