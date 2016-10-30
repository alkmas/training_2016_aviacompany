package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.traininng2016.aviacompany.daodb.customentity.FlightWithAirport;

@Repository
public interface FlightDao extends BaseDao<Flight> {
	FlightWithAirport getWithAirport(Long id);
	List<FlightWithAirport> getAllWithAirport();
	List<FlightWithAirport> getAllForDayWeek(Long dayWeek);
}
