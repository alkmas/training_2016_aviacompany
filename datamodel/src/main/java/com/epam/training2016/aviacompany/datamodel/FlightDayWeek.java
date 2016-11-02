package com.epam.training2016.aviacompany.datamodel;

public class FlightDayWeek {
	private Long flightId;
	private Long dayWeek;
	
	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public Long getDayWeek() {
		return dayWeek;
	}
	public void setDayWeek(Long dayWeek) {
		this.dayWeek = dayWeek;
	}
	@Override
	public String toString() {
		return "FlightDayWeek [dayWeek=" + dayWeek + "]";
	}
	
	public boolean equals(FlightDayWeek obj) {
		if ((this.flightId == obj.getFlightId())
				&& (this.dayWeek == obj.getDayWeek())) {
			return true;			
		}
		return false;
	}
	
	public boolean filter(FlightDayWeek objFilter) {
		if (((this.flightId == objFilter.getFlightId()) || (objFilter.getFlightId() == null))
				&& ((this.dayWeek == objFilter.getDayWeek()) || (objFilter.getDayWeek() == null))) {
			return true;
		}
		return false;
	}
	
}
