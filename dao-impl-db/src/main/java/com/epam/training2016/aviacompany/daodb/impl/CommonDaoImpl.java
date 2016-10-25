package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.CommonDao;


public class CommonDaoImpl<T> implements CommonDao<T> {
	private Class<T> classT;
	
    @Inject
    private JdbcTemplate jdbcTemplate;
	
    @Inject
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String addNameTableInSQL(String sql) {
    	return String.format(sql, classT.getSimpleName());
    }
    
	@Override
	public T get(Long id) {
		
		return jdbcTemplate.queryForObject(
				addNameTableInSQL("SELECT * FROM %s WHERE id=?"),
				new Object[] {id}, 
				new BeanPropertyRowMapper<T>(classT));
	}

	@Override
	public void insert(T entity) {
		namedParameterJdbcTemplate.update(
				addNameTableInSQL("INSERT INTO %s(name) VALUES(:name)"),
				new BeanPropertySqlParameterSource(entity));
	}

	@Override
	public void update(T entity) {
		namedParameterJdbcTemplate.update(
				addNameTableInSQL("UPDATE %s SET name=:name WHERE id=:id"),
				new BeanPropertySqlParameterSource(entity));
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update(addNameTableInSQL("DELETE FROM %s WHERE id=?"), new Object[]{id});
	}

	@Override
	public List<T> getAll() {
		return jdbcTemplate.query(
				addNameTableInSQL("SELECT * FROM %s"),
				new BeanPropertyRowMapper<T>(classT));
	}

}
