package com.epam.training2016.aviacompany.services.utils;

public class IdNullException extends Exception{
	
	private static final long serialVersionUID = -3928441316019478440L;

	public static void CheckIdParameter(Long id) throws IdNullException {
		if (id == null) {
			throw new IdNullException();
		}
	}

}
