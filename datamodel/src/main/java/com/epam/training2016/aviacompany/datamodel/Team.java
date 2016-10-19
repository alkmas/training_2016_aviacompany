package com.epam.training2016.aviacompany.datamodel;

public class Team extends AbstractModel {
	private Long countPilot;
	private Long countNavigator;
	private Long countRadioman;
	private Long countStewardess;
	
	public Team(Long countPilot, Long countNavigator, Long countRadioman, Long countStewardess) {
		super();
		this.countPilot = countPilot;
		this.countNavigator = countNavigator;
		this.countRadioman = countRadioman;
		this.countStewardess = countStewardess;
	}

	public Long getCountPilot() {
		return countPilot;
	}
	public void setCountPilot(Long countPilot) {
		this.countPilot = countPilot;
	}
	public Long getCountNavigator() {
		return countNavigator;
	}
	public void setCountNavigator(Long countNavigator) {
		this.countNavigator = countNavigator;
	}
	public Long getCountRadioman() {
		return countRadioman;
	}
	public void setCountRadioman(Long countRadioman) {
		this.countRadioman = countRadioman;
	}
	public Long getCountStewardess() {
		return countStewardess;
	}
	public void setCountStewardess(Long countStewardess) {
		this.countStewardess = countStewardess;
	}
}
