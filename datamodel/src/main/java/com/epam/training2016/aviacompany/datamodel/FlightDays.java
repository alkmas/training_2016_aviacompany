package com.epam.training2016.aviacompany.datamodel;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.stereotype.Component;

/**
 * Класс Полеты по дням недели
 * для класса Flight
 * @author alex
 *
 */
@Component
public class FlightDays extends AbstractModel implements Serializable {
	private static final long serialVersionUID = 1L;
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

	public Boolean[] getDays() {
		return days;
	}

	public void setDays(Boolean[] days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "FlightDays (id=" + this.getId() + ") [days=" + Arrays.toString(days) + "]";
	}
	
}
