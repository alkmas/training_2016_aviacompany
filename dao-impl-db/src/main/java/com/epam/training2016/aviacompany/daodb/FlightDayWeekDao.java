package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.FlightDayWeek;

@Repository
public interface FlightDayWeekDao {
	List<FlightDayWeek> get(Long id);
	void insert(FlightDayWeek entity);
	void delete(FlightDayWeek entity);
}
