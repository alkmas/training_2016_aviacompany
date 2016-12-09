package com.epam.training2016.aviacompany.web.components;

import org.springframework.stereotype.Component;

@Component
//@Scope(value = "request")
public class UserDataStorage {

	private String DEFAULT_LOCALE = "ru_RU";
    private boolean isLoggedIn;
    private String locale;
    
    public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		if (locale == null) {
			this.locale = DEFAULT_LOCALE;
		}
		this.locale = locale;
	}

	public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    @Override
    public String toString() {
        return "UserDataStorage [isLoggedIn=" + isLoggedIn + ";hashCode=" + hashCode() + "]";
    }

}
