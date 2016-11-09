package com.epam.training2016.aviacompany.datamodel;

/**
 * Класс Полеты по дням недели
 * для класса Flight
 * @author alex
 *
 */
public class FlightDays {
	private Long flightId;
	private Long dayWeek;
	private Boolean day1;
	private Boolean day2;
	private Boolean day3;
	private Boolean day4;
	private Boolean day5;
	private Boolean day6;
	private Boolean day7;
	
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
	public Boolean getDay1() {
		return day1;
	}
	public void setDay1(Boolean day1) {
		this.day1 = day1;
	}
	public Boolean getDay2() {
		return day2;
	}
	public void setDay2(Boolean day2) {
		this.day2 = day2;
	}
	public Boolean getDay3() {
		return day3;
	}
	public void setDay3(Boolean day3) {
		this.day3 = day3;
	}
	public Boolean getDay4() {
		return day4;
	}
	public void setDay4(Boolean day4) {
		this.day4 = day4;
	}
	public Boolean getDay5() {
		return day5;
	}
	public void setDay5(Boolean day5) {
		this.day5 = day5;
	}
	public Boolean getDay6() {
		return day6;
	}
	public void setDay6(Boolean day6) {
		this.day6 = day6;
	}
	public Boolean getDay7() {
		return day7;
	}
	public void setDay7(Boolean day7) {
		this.day7 = day7;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day1 == null) ? 0 : day1.hashCode());
		result = prime * result + ((day2 == null) ? 0 : day2.hashCode());
		result = prime * result + ((day3 == null) ? 0 : day3.hashCode());
		result = prime * result + ((day4 == null) ? 0 : day4.hashCode());
		result = prime * result + ((day5 == null) ? 0 : day5.hashCode());
		result = prime * result + ((day6 == null) ? 0 : day6.hashCode());
		result = prime * result + ((day7 == null) ? 0 : day7.hashCode());
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
		FlightDays other = (FlightDays) obj;
		if (day1 == null) {
			if (other.day1 != null)
				return false;
		} else if (!day1.equals(other.day1))
			return false;
		if (day2 == null) {
			if (other.day2 != null)
				return false;
		} else if (!day2.equals(other.day2))
			return false;
		if (day3 == null) {
			if (other.day3 != null)
				return false;
		} else if (!day3.equals(other.day3))
			return false;
		if (day4 == null) {
			if (other.day4 != null)
				return false;
		} else if (!day4.equals(other.day4))
			return false;
		if (day5 == null) {
			if (other.day5 != null)
				return false;
		} else if (!day5.equals(other.day5))
			return false;
		if (day6 == null) {
			if (other.day6 != null)
				return false;
		} else if (!day6.equals(other.day6))
			return false;
		if (day7 == null) {
			if (other.day7 != null)
				return false;
		} else if (!day7.equals(other.day7))
			return false;
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
		return "FlightDays [flightId=" + flightId + ", dayWeek=" + dayWeek + ", day1=" + day1 + ", day2=" + day2
				+ ", day3=" + day3 + ", day4=" + day4 + ", day5=" + day5 + ", day6=" + day6 + ", day7=" + day7 + "]";
	}
	
	
}
