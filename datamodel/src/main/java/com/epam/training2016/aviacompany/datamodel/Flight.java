package com.epam.training2016.aviacompany.datamodel;

import java.util.Date;

public class Flight extends AbstractModel {
	private String name;
	private String way;
	private Date awayDate;
	private Date arrivalDate;
	private String terminal;
	
	
	public Flight(String name, String way, Date awayDate, Date arrivalDate) {
		super();
		this.name = name;
		this.way = way;
		this.awayDate = awayDate;
		this.arrivalDate = arrivalDate;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public Date getAwayDate() {
		return awayDate;
	}
	public void setAwayDate(Date awayDate) {
		this.awayDate = awayDate;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
}
