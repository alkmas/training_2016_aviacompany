package com.epam.training2016.aviacompany.daodb.impl;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Airport;

@Repository
public class AirportDaoImpl extends CommonDaoImpl<Airport>{

}
