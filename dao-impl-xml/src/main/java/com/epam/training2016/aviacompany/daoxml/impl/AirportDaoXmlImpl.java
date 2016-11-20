package com.epam.training2016.aviacompany.daoxml.impl;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.epam.training2016.aviacompany.datamodel.Airport;

@Repository
public class AirportDaoXmlImpl extends BaseDaoXmlImpl<Airport> {

	
	@Override
	public Class<Airport> getGenericType() {
		return Airport.class;
	}


}
