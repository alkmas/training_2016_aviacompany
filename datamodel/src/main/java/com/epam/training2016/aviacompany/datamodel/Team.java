package com.epam.training2016.aviacompany.datamodel;

public class Team extends AbstractModel {
	private Long pilot;
	private Long navigator;
	private Long radioman;
	private Long stewardess1;
	private Long stewardess2;
	
	public Long getPilot() {
		return pilot;
	}
	public void setPilot(Long pilot) {
		this.pilot = pilot;
	}
	public Long getNavigator() {
		return navigator;
	}
	public void setNavigator(Long navigator) {
		this.navigator = navigator;
	}
	public Long getRadioman() {
		return radioman;
	}
	public void setRadioman(Long radioman) {
		this.radioman = radioman;
	}
	public Long getStewardess1() {
		return stewardess1;
	}
	public void setStewardess1(Long stewardess1) {
		this.stewardess1 = stewardess1;
	}
	public Long getStewardess2() {
		return stewardess2;
	}
	public void setStewardess2(Long stewardess2) {
		this.stewardess2 = stewardess2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((navigator == null) ? 0 : navigator.hashCode());
		result = prime * result + ((pilot == null) ? 0 : pilot.hashCode());
		result = prime * result + ((radioman == null) ? 0 : radioman.hashCode());
		result = prime * result + ((stewardess1 == null) ? 0 : stewardess1.hashCode());
		result = prime * result + ((stewardess2 == null) ? 0 : stewardess2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (navigator == null) {
			if (other.navigator != null)
				return false;
		} else if (!navigator.equals(other.navigator))
			return false;
		if (pilot == null) {
			if (other.pilot != null)
				return false;
		} else if (!pilot.equals(other.pilot))
			return false;
		if (radioman == null) {
			if (other.radioman != null)
				return false;
		} else if (!radioman.equals(other.radioman))
			return false;
		if (stewardess1 == null) {
			if (other.stewardess1 != null)
				return false;
		} else if (!stewardess1.equals(other.stewardess1))
			return false;
		if (stewardess2 == null) {
			if (other.stewardess2 != null)
				return false;
		} else if (!stewardess2.equals(other.stewardess2))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Team [pilot=" + pilot + ", navigator=" + navigator + ", radioman=" + radioman + ", stewardess1="
				+ stewardess1 + ", stewardess2=" + stewardess2 + "]";
	}

	
	
}
