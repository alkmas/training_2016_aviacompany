package com.epam.training2016.aviacompany.services.impl;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.services.AuthenticationService;

@Repository
public class AuthenticationServiceImpl implements AuthenticationService {

	@Override
	public boolean validateUserPassword(String username, String password) {
		return username.equals("aviacompany") && password.equals("SeQuRiTy");
		// YXZpYWNvbXBhbnk6U2VRdVJpVHk=
	}

}
