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

	public boolean equals(Flight obj) {
		if (super.equals(obj) && this.name.equals(obj.getName()) 
				&& (this.airportSrcId == obj.getAirportSrcId())
				&& (this.airportDstId == obj.getAirportDstId()) 
				&& (this.departureTime.getTime() == obj.getDepartureTime().getTime())
				&& (this.arrivalTime.getTime() == obj.getArrivalTime().getTime())) {
			return true;
		}
		return false;
	}

	public boolean filter(Flight objFilter) {
		if (super.filter(objFilter)
				&& (this.name.equals(objFilter.getName()) || (objFilter.getName() == null))
				&& ((this.airportSrcId == objFilter.getAirportSrcId()) || (objFilter.getAirportSrcId() == null))
				&& ((this.airportDstId == objFilter.getAirportDstId()) || (objFilter.getAirportDstId() == null))
				&& ((this.departureTime.getTime() == objFilter.getDepartureTime().getTime()) || (objFilter.getDepartureTime() == null))
				&& ((this.arrivalTime.getTime() == objFilter.getArrivalTime().getTime()) || (objFilter.getArrivalTime() == null))) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Flight [name=" + name + ", airportSrcId=" + airportSrcId + ", airportDstId=" + airportDstId
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + "]";
	}

}
