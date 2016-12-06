package com.epam.training2016.aviacompany.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request")
public class UserDataStorage {

    private boolean isLoggedIn;
    private String locale;
    
    public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
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
