package com.epam.training2016.aviacompany.datamodel;

public class JobTitle extends AbstractModel {
	private String name;
	
	public JobTitle(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
