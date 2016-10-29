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
	
}
