package com.epam.training2016.aviacompany.datamodel;

/**
 * Класс Аэропорт
 * @author alex
 *
 */
public class Airport extends AbstractModel {
	static private String[] UNIQUE_KEYS = {"id", "name"};
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String[] getUniqueKeys() {
		return UNIQUE_KEYS;
	}

	@Override
	public String toString() {
		return "Airport [" + name + "]";
	}

}
