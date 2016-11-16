package com.epam.training2016.aviacompany.daoapi;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Flight;

@Repository
public interface IFlightDao extends IBaseDao<Flight> {
//	FlightWithAirport getWithAirport(Long id);
//	List<FlightWithAirport> getAllWithAirport();
//	List<FlightWithAirport> getAllForDays(Long dayWeek);
	List<Flight> getAllForDays(Long dayWeek);
	void deleteByFlightId(Long flightId);
	void deleteByAirportSrcId(Long airportId);
	void deleteByAirportDstId(Long airportId);
}
