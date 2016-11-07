package com.epam.training2016.aviacompany.datamodel;

import java.sql.Date;

/**
 * Класс Рейс с членами экипажа
 * член экипажа может быть назначен на рейс один раз в день
 * данное условие также определено в БД
 * @author alex
 *
 */
public class Flight2Employee extends AbstractModel {
	private Long flightId;
	private Long employeeId;
	private Date departure;
	
	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((flightId == null) ? 0 : flightId.hashCode());
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
		Flight2Employee other = (Flight2Employee) obj;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (flightId == null) {
			if (other.flightId != null)
				return false;
		} else if (!flightId.equals(other.flightId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight2Employee [flightId=" + flightId + ", employeeId=" + employeeId + ", departure=" + departure
				+ "]";
	}
	
}
