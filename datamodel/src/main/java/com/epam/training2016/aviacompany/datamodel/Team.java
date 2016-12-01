package com.epam.training2016.aviacompany.datamodel;

import java.io.Serializable;

public class Team extends AbstractModel implements Serializable {
	private static final long serialVersionUID = 1L;
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
	public String toString() {
		return "Team [pilot=" + pilot + ", navigator=" + navigator + ", radioman=" + radioman + ", stewardess1="
				+ stewardess1 + ", stewardess2=" + stewardess2 + "]";
	}

	
	
}
