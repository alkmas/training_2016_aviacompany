package com.epam.training2016.aviacompany.datamodel;

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
	
	public boolean equals(Airport obj) {
		if (super.equals(obj) && this.name.equals(obj.getName())) {
			return true;			
		}
		return false;
	}

	public boolean filter(Airport objFilter) {
		if (super.filter(objFilter)	
				&& (this.name.equals(objFilter.getName()) 
						|| (objFilter.getName() == null))) {
			return true;
		}
		return false;
	}
}
