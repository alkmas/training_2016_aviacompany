package com.epam.training2016.aviacompany.services;

import java.util.Date;
import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

public interface Flight2EmployeeService extends BaseService<Flight> {
	List<FlightWithAirport> getAllForAway(Date date);
}
