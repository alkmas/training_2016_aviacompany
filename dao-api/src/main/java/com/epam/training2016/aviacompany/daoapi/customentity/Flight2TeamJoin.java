package com.epam.training2016.aviacompany.daoapi.customentity;

import java.sql.Date;

import com.epam.training2016.aviacompany.datamodel.Flight;
import com.epam.training2016.aviacompany.datamodel.Team;

public class Flight2TeamJoin {
	private Long id;
	private Flight flight;
	private Team team;
	private Date departure;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	

}
