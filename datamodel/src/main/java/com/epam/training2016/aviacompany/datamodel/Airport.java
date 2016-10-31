package com.epam.training2016.aviacompany.datamodel;

public class Airport extends AbstractModel {
	public final static String SQL_UPDATE = "";
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Airport [name=" + name + "]";
	}
	
	

}
