package com.epam.training2016.aviacompany.datamodel;

import java.util.Date;

public class Flight extends AbstractModel {
	private Date arrival;
	private Long teamId;
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	
	
}
