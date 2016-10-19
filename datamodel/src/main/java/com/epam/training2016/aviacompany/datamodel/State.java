package com.epam.training2016.aviacompany.datamodel;

public class State extends AbstractModel {
	private String name;
	private Boolean needDate;
	
		
	public State(String name) {
		super();
		this.name = name;
		this.needDate = false;
	}
	
	public State(String name, Boolean needDate) {
		this(name);
		this.needDate = needDate;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getNeedDate() {
		return needDate;
	}
	public void setNeedDate(Boolean needDate) {
		this.needDate = needDate;
	}
	
	
	
}
