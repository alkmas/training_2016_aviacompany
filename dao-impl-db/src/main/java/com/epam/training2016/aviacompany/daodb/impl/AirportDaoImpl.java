package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.CommonDao;
import com.epam.training2016.aviacompany.datamodel.Airport;

@Repository
public class AirportDaoImpl implements CommonDao<Airport>{
    @Inject
    private JdbcTemplate jdbcTemplate;
	
    @Inject
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String addNameTableInSQL(String sql) {
    	return String.format(sql, Airport.class.getSimpleName());
    }
    
	@Override
	public Airport get(Long id) {
				return jdbcTemplate.queryForObject(
				addNameTableInSQL("SELECT * FROM %s WHERE id=?"),
				new Object[] {id}, 
				new BeanPropertyRowMapper<Airport>(Airport.class));
	}

	@Override
	public void insert(Airport entity) {
		namedParameterJdbcTemplate.update(
				addNameTableInSQL("INSERT INTO %s(name) VALUES(:name)"),
				new BeanPropertySqlParameterSource(entity));
	}

	@Override
	public void update(Airport entity) {
		namedParameterJdbcTemplate.update(
				addNameTableInSQL("UPDATE %s SET name=:name WHERE id=:id"),
				new BeanPropertySqlParameterSource(entity));
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update(addNameTableInSQL("DELETE FROM %s WHERE id=?"), new Object[]{id});
	}

	@Override
	public List<Airport> getAll() {
		return jdbcTemplate.query(
				addNameTableInSQL("SELECT * FROM %s"),
				new BeanPropertyRowMapper<Airport>(Airport.class));
	}


}
