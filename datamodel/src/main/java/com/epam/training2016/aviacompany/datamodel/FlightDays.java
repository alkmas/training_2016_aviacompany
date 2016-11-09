package com.epam.training2016.aviacompany.datamodel;

/**
 * Класс Полеты по дням недели
 * для класса Flight
 * @author alex
 *
 */
public class FlightDayWeek {
	private Long flightId;
	private Long dayWeek;
	
	public FlightDayWeek() {

	}
	
	public FlightDayWeek(Long flightId, Long dayWeek) {
		this.flightId = flightId;
		this.dayWeek = dayWeek;
	}
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayWeek == null) ? 0 : dayWeek.hashCode());
		result = prime * result + ((flightId == null) ? 0 : flightId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightDayWeek other = (FlightDayWeek) obj;
		if (dayWeek == null) {
			if (other.dayWeek != null)
				return false;
		} else if (!dayWeek.equals(other.dayWeek))
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
		return "FlightDayWeek [flightId=" + flightId + ", dayWeek=" + dayWeek + "]";
	}
	
	
}
