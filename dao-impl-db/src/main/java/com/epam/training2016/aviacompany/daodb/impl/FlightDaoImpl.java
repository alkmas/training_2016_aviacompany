package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.BaseDao;
import com.epam.training2016.aviacompany.datamodel.Flight;

@Repository
public class FlightDaoImpl extends BaseDaoImpl<Flight> {

	FlightDaoImpl() {
		super(Flight.class);
		// TODO Auto-generated constructor stub
	}
}
