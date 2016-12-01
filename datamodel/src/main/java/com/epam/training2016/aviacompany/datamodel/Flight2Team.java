package com.epam.training2016.aviacompany.datamodel;

import java.io.Serializable;
import java.sql.Date;

/**
 * Класс Рейс с бригадой
 * бригада может быть назначена на рейс один раз в день
 * данное условие также определено в БД
 * @author alex
 *
 */
public class Flight2Team extends AbstractModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long flightId;
	private Long teamId;
	private Date departure;
	
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
	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	@Override
	public String toString() {
		return "Flight2Team [flightId=" + flightId + ", teamId=" + teamId + ", departure=" + departure + "]";
	}
	
	
	
	
}
