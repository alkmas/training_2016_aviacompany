package com.epam.training2016.aviacompany.datamodel;

import java.util.Date;

public class Team2Flight {
	private Long teamId;
	private Long flightId;
	private Date arrival;
	
	public Team2Flight(Long teamId, Long flightId) {
		super();
		this.teamId = teamId;
		this.flightId = flightId;
	}
	
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}


}
