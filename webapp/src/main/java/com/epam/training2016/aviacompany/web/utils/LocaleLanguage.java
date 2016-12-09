package com.epam.training2016.aviacompany.web.utils;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.inject.Inject;

import com.epam.training2016.aviacompany.web.components.UserDataStorage;

public class LocaleLanguage {
	private String resourceName;
	private ResourceBundle resource;
    @Inject
    private UserDataStorage userDataStorage;

	public LocaleLanguage() {
		
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
		resource = ResourceBundle.getBundle(resourceName, new Locale(userDataStorage.getLocale()));
	}

	
	
}
