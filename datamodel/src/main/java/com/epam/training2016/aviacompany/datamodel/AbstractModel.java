package com.epam.training2016.aviacompany.datamodel;

public class AbstractModel {
    private Long id;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean equals(AbstractModel obj) {
		if (obj.getId() == this.id) {
			return true;
		}
		return false;
	}

}
