package com.epam.training2016.aviacompany.datamodel;

import java.sql.Time;
import java.util.List;

public class Flight extends AbstractModel {
	private String name;
	private Long airportSrcId;
	private Long airportDstId;
	private Time awayTime;
	private Time arrivalTime;
	private List<Long> daysWeek;
	
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
	
	public Time getAwayTime() {
		return awayTime;
	}
	
	public void setAwayTime(Time awayTime) {
		this.awayTime = awayTime;
	}
	
	public Time getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public List<Long> getDaysWeek() {
		return daysWeek;
	}

	public void setDaysWeek(List<Long> daysWeek) {
		this.daysWeek = daysWeek;
	}
}
