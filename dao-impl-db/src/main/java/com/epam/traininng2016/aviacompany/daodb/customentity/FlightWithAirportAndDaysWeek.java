package com.epam.traininng2016.aviacompany.daodb.customentity;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDayWeek;

@Repository
public class FlightWithAirportAndDaysWeek {
	private Flight flight;
	private Airport airportSrc;
	private Airport airportDst;
	private List<FlightDayWeek> dayWeeks;
	
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
	public List<FlightDayWeek> getDayWeeks() {
		return dayWeeks;
	}
	public void setDayWeeks(List<FlightDayWeek> dayWeeks) {
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
