package com.epam.training2016.aviacompany.daodb;

import java.sql.Date;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight2Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithEmployee;

public interface Flight2EmployeeDao extends BaseDao<Flight2Employee> {
	List<Flight2Employee> getByFlightId(Long id);
	List<Flight2Employee> getByEmployeeId(Long id);
	void deleteByFlightIdAndDate(Long flightId, Date date);
}
