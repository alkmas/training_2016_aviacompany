package com.epam.training2016.aviacompany.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daodb.impl.Flight2EmployeeDaoImpl;
import com.epam.training2016.aviacompany.datamodel.Flight2Employee;
import com.epam.training2016.aviacompany.services.BaseService;

@Service
public class Flight2EmployeeServiceImpl implements BaseService<Flight2Employee> {
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
	public Long save(Flight2Employee entity) {
		if (entity.getId() == null) {
			return flight2EmployeeDao.insert(entity);
		} else {
			flight2EmployeeDao.update(entity);
			return entity.getId();
		}
	}

	@Override
	public boolean isDaoExist() {
		return flight2EmployeeDao != null;
	}

	@Override
	public Flight2Employee get(Long id) {
		return flight2EmployeeDao.get(id);
	}

	@Override
	public List<Flight2Employee> getAll() {
		return flight2EmployeeDao.getAll();
	}

}
