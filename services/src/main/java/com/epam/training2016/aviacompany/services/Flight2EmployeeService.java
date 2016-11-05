package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight2Employee;
import com.epam.training2016.aviacompany.services.utils.IdNullException;

public interface Flight2EmployeeService extends BaseService<Flight2Employee>{
	List<Flight2Employee> getByFlightId(Long id) throws IdNullException;
	List<Flight2Employee> getByDeparture(Date dt);
	void deleteByEmployeeId(Long employeeId) throws IdNullException;
	void deleteByFlightId(Long flightId) throws IdNullException;
}
