package com.epam.training2016.aviacompany.datamodel;

import java.util.Date;

public class FlightToState extends AbstractModel {
	private Long flightId;
	private Long stateId;
	private Date infoDate;
	private Date createDate;
	
	
	public FlightToState(Long flightId, Long stateId) {
		super();
		this.flightId = flightId;
		this.stateId = stateId;
		this.infoDate = null;
		this.createDate = new Date();
	}

	
	public FlightToState(Long flightId, Long stateId, Date infoDate) {
		this(flightId, stateId);
		this.infoDate = infoDate;
	}


	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Date getInfoDate() {
		return infoDate;
	}
	public void setInfoDate(Date infoDate) {
		this.infoDate = infoDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
