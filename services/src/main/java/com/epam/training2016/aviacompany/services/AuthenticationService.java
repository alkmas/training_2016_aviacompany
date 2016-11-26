package com.epam.training2016.aviacompany.services;

public interface AuthenticationService {
	boolean validateUserPassword(String username, String password);
}
