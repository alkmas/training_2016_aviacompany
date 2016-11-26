package com.epam.training2016.aviacompany.daoapi.customentity;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.FlightDays;

@Repository
public class FlightWithAirportsAndDaysWeek {
	private Flight flight;
	private Airport airportSrc;
	private Airport airportDst;
	private FlightDays days;
	

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


	public FlightDays getDays() {
		return days;
	}


	public void setDays(FlightDays days) {
		this.days = days;
	}


	@Override
	public String toString() {
		return "FlightWithAirportAndDaysWeek [flight=" + flight.getName() + 
				", airportSrc=" + airportSrc.getName() + 
				", airportDst="	+ airportDst.getName() + 
				", daysWeek=" + days.getDays() + "]";
	}

	
}
