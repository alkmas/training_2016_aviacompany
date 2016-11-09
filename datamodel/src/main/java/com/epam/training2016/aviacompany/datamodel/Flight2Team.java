package com.epam.training2016.aviacompany.datamodel;

import java.sql.Date;

/**
 * Класс Рейс с бригадой
 * бригада может быть назначена на рейс один раз в день
 * данное условие также определено в БД
 * @author alex
 *
 */
public class Flight2Team extends AbstractModel {
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((flightId == null) ? 0 : flightId.hashCode());
		result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight2Team other = (Flight2Team) obj;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (flightId == null) {
			if (other.flightId != null)
				return false;
		} else if (!flightId.equals(other.flightId))
			return false;
		if (teamId == null) {
			if (other.teamId != null)
				return false;
		} else if (!teamId.equals(other.teamId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Flight2Team [flightId=" + flightId + ", teamId=" + teamId + ", departure=" + departure + "]";
	}
	
	
	
	
}
