package com.epam.training2016.aviacompany.services;

import java.sql.Date;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight2Employee;

public interface Flight2EmployeeService extends BaseService<Flight2Employee>{
	List<Flight2Employee> getByFlightId(Long id);
	List<Flight2Employee> getByDeparture(Date dt);
}
