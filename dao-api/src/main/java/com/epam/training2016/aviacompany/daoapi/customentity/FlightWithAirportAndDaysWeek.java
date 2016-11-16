package com.epam.training2016.aviacompany.daoapi.customentity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDays;

@Repository
public class FlightWithAirportAndDaysWeek {
	private Flight flight;
	private Airport airportSrc;
	private Airport airportDst;
	private List<FlightDays> dayWeeks;
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Airport getAirportSrc() {
		return airportSrc;
	}
	public void setAirportSrc(Airport airportSrc) {
		this.airportSrc = airportSrc;
	}
	public Airport getAirportDst() {
		return airportDst;
	}
	public void setAirportDst(Airport airportDst) {
		this.airportDst = airportDst;
	}
	public List<FlightDays> getDayWeeks() {
		return dayWeeks;
	}
	public void setDayWeeks(List<FlightDays> dayWeeks) {
		this.dayWeeks = dayWeeks;
	}

	@Override
	public String toString() {
		return "FlightWithAirportAndDaysWeek [flight=" + flight.getName() + 
				", airportSrc=" + airportSrc.getName() + 
				", airportDst="	+ airportDst.getName() + 
				", dayWeeks=" + dayWeeks + "]";
	}

	
}
