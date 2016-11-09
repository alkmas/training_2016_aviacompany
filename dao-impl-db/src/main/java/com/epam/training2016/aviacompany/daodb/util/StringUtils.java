package com.epam.training2016.aviacompany.daodb.util;

public class StringUtils {
	public static String getSimpleName(String name) {
		return name.substring(name.lastIndexOf('.') + 1);
	}
	
	public static String toDbFormat(String name) {
		return name.replaceAll("(?=[A-Z24])", "_").toLowerCase().substring(1);
	}
}
