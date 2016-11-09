package com.epam.training2016.aviacompany.daodb;

import java.sql.Date;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight2Team;

public interface Flight2EmployeeDao extends BaseDao<Flight2Team> {
	List<Flight2Team> getByFlightId(Long flightId);
	List<Flight2Team> getByEmployeeId(Long employeeId);
	Flight2Team getByEmployeeIdAndDate(Long employeeId, Date date);
	void deleteByFlightIdAndDate(Long flightId, Date date);
	void deleteByEmployeeId(Long employeeId);
}
