package com.epam.training2016.aviacompany.daoapi.customentity;

import java.io.Serializable;

import com.epam.training2016.aviacompany.datamodel.Airport;
import com.epam.training2016.aviacompany.datamodel.Flight;

public class FlightWithAirport implements Serializable {
	private static final long serialVersionUID = 1L;
	private Flight flight;
	private Airport airportSrc;
	private Airport airportDst;
	
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
	@Override
	public String toString() {
		return "FlightWithAirport [flight=" + flight 
				+ ", airportSrc=" + airportSrc 
				+ ", airportDst=" + airportDst + "]";
	}

	
}
