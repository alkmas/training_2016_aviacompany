package com.epam.training2016.aviacompany.web.model;

public class AirportModel {
    private Long id;
    private String name;
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "AirportModel [id=" + id + ", name=" + name + "]";
	}
}
