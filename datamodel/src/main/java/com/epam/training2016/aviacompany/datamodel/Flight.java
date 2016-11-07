package com.epam.training2016.aviacompany.datamodel;

import java.sql.Time;

/**
 * Класс Расписание полетов
 * вылет по рейсу раз в день
 * дни недели по рейсу в классе FlightDayWeek 
 * @author alex
 *
 */
public class Flight extends AbstractModel {
	private String name;
	private Long airportSrcId;
	private Long airportDstId;
	private Time departureTime;
	private Time arrivalTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAirportSrcId() {
		return airportSrcId;
	}

	public void setAirportSrcId(Long airportSrcId) {
		this.airportSrcId = airportSrcId;
	}

	public Long getAirportDstId() {
		return airportDstId;
	}

	public void setAirportDstId(Long airportDstId) {
		this.airportDstId = airportDstId;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((airportDstId == null) ? 0 : airportDstId.hashCode());
		result = prime * result + ((airportSrcId == null) ? 0 : airportSrcId.hashCode());
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Flight other = (Flight) obj;
		if (airportDstId == null) {
			if (other.airportDstId != null)
				return false;
		} else if (!airportDstId.equals(other.airportDstId))
			return false;
		if (airportSrcId == null) {
			if (other.airportSrcId != null)
				return false;
		} else if (!airportSrcId.equals(other.airportSrcId))
			return false;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [name=" + name + ", airportSrcId=" + airportSrcId + ", airportDstId=" + airportDstId
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + "]";
	}

}
