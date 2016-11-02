package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight2Employee;

public interface Flight2EmployeeDao extends BaseDao<Flight2Employee> {
	List<Flight2Employee> getByFlightId(Long id);
	List<Flight2Employee> getByEmployeeId(Long id);
}
