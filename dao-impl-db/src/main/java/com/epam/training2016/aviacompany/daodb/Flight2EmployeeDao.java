package com.epam.training2016.aviacompany.daodb;

import java.sql.Date;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight2Employee;

public interface Flight2EmployeeDao extends BaseDao<Flight2Employee> {
	List<Flight2Employee> getByFlightId(Long flightId);
	List<Flight2Employee> getByEmployeeId(Long employeeId);
	Flight2Employee getByEmployeeIdAndDate(Long employeeId, Date date);
	void deleteByFlightIdAndDate(Long flightId, Date date);
	void deleteByEmployeeId(Long employeeId);
}
