package com.epam.training2016.aviacompany.web.model;

import java.sql.Date;

public class Flight2TeamModel {
	private Long flightId;
	private Long teamId;
	private String departure;
	
	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	
	
}
