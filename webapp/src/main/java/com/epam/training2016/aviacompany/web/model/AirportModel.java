package com.epam.training2016.aviacompany.web.model;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.epam.training2016.aviacompany.services.components.UserDataStorage;

public class AirportModel {
    private Long id;
    private String name;
    private ResourceBundle resource;
    @Inject
    private UserDataStorage userDataStorage;
    
	public AirportModel() {
		resource = ResourceBundle.getBundle("airport", new Locale(userDataStorage.getLocale()));
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return resource.getString(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "AirportModel [id=" + id + ", name=" + name + "]";
	}
}
