package com.epam.training2016.aviacompany.datamodel;

/**
 * Класс Полеты по дням недели
 * для класса Flight
 * @author alex
 *
 */
public class FlightDays extends AbstractModel{
	private Boolean[] days;
	
	public FlightDays() {
		super();
		this.days = new Boolean[] {false,false,false,false,false,false,false};
	}
	
	public Boolean getDay(int numDay) {
		return days[numDay-1];
	}
	
	public void setDay(int numDay, Boolean value) {
		days[numDay-1] = value;
	}
	
	
}
