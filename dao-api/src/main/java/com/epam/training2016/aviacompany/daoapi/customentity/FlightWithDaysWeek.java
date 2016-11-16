package com.epam.training2016.aviacompany.daoapi.customentity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDays;

@Repository
public class FlightWithDaysWeek {
	private Flight flight;
	private List<FlightDays> dayWeeks;
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public List<FlightDays> getDayWeeks() {
		return dayWeeks;
	}
	public void setDayWeeks(List<FlightDays> dayWeeks) {
		this.dayWeeks = dayWeeks;
	}
	@Override
	public String toString() {
		return "FlightWithDaysWeek [flight=" + flight + ", dayWeeks=" + dayWeeks + "]";
	}
	

}
