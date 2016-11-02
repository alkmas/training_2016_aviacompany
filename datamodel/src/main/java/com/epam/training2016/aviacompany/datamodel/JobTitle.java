package com.epam.training2016.aviacompany.datamodel;

public class JobTitle extends AbstractModel {
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(JobTitle obj) {
		if (this.name.equals(obj.getName())) return true;			
		return false;
	}

	public boolean filter(JobTitle objFilter) {
		if (super.filter(objFilter)	
				&& (this.name.equals(objFilter.getName()) 
						|| (objFilter.getName() == null))) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "JobTitle [name=" + name + "]";
	}
	
}
