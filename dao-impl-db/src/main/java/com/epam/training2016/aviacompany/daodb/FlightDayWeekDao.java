package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.FlightDays;

@Repository
public interface FlightDayWeekDao {
	List<FlightDays> getByFlightId(Long id);
	void insert(FlightDays entity);
	void delete(FlightDays entity);
	void deleteByFlightId(Long flightId);
}
