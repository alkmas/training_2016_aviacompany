package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.CommonDao;
import com.epam.training2016.aviacompany.datamodel.Flight;

@Repository
public class FlightDaoImpl implements CommonDao<Flight> {
    @Inject
    private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Flight get(Long id) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM flight WHERE id=?",
				new Object[] {id}, 
				new BeanPropertyRowMapper<Flight>(Flight.class));
	}

	@Override
	public void insert(Flight entity) {
		jdbcTemplate.update("INSERT INTO flight(name) VALUES(?)", entity.getName());
	}

	@Override
	public void update(Flight entity) {
		jdbcTemplate.update("UPDATE flight SET name=? WHERE id=?", entity.getName(), entity.getId());
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM flight WHERE id=?", new Object[]{id});
	}

	@Override
	public List<Flight> getAll() {
		return jdbcTemplate.query(
				"SELECT * FROM flight",
				new BeanPropertyRowMapper<Flight>(Flight.class));
	}

}
