package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.CommonDao;
import com.epam.training2016.aviacompany.datamodel.Airport;

@Repository
public class AirportDaoImpl implements CommonDao<Airport> {
    @Inject
    private JdbcTemplate jdbcTemplate;
    
	@Override
    public Airport get(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from airport where id = ?",
                new Object[] { id }, new BeanPropertyRowMapper<Airport>(
                		Airport.class));
    }

	@Override
	public void insert(Airport entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Airport entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Airport> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
