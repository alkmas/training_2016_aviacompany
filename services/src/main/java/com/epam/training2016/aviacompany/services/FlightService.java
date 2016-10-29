package com.epam.training2016.aviacompany.services;

import java.util.List;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirportAndDaysWeek;

public interface FlightService extends BaseService<Flight> {
	FlightWithAirportAndDaysWeek getFlight(Long id);
	FlightWithAirportAndDaysWeek getFlight(Flight flight);
	List<FlightWithAirportAndDaysWeek> getAllFlight();
	List<FlightWithAirportAndDaysWeek> getFlights(List<Flight> flights);
}
