package com.epam.training2016.aviacompany.datamodel;

public class DayWeek {
	private String name;
	private String longName;
	
	public DayWeek(String name, String longName) {
		this.name = name;
		this.longName = longName;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	
	
}
