package com.epam.training2016.aviacompany.datamodel;

/**
 * Класс Должности сотрудников компании
 * @author alex
 *
 */
public class JobTitle extends AbstractModel {
	private String name;
	
	public JobTitle() {
		super();
	}
	
	public JobTitle(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "JobTitle [name=" + name + "]";
	}
	
}
