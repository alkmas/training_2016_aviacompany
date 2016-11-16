package com.epam.training2016.aviacompany.datamodel;

/**
 * Класс Аэропорт
 * @author alex
 *
 */
public class Airport extends AbstractModel {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Airport [" + name + "]";
	}

}
