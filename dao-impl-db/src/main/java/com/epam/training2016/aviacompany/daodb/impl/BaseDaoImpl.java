package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.training2016.aviacompany.daodb.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {
	private Class<T> type;
	private String nameTable;
	public final String SQL_UPDATE = "UPDATE %s SET name=:name WHERE id=:id";

	@Inject
	protected JdbcTemplate jdbcTemplate;
	@Inject
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	BaseDaoImpl(Class<T> type, String nameTable) {
		this.type = type;
		this.nameTable = nameTable;
	}

	public String getSQLUpdate() {
		return SQL_UPDATE;
	}
	
	@Override
	public T getById(Long id) {
		String sql = "SELECT * FROM " + this.nameTable +" WHERE id=?";
		return jdbcTemplate.queryForObject(sql, 
				new Object[] { id }, 
				new BeanPropertyRowMapper<T>(type));
	}
	
	@Override
	public Long insert(T entity) {
		SimpleJdbcInsert createCustomer = new SimpleJdbcInsert(jdbcTemplate)
				   .withTableName(this.nameTable)
				   .usingGeneratedKeyColumns("id");
		KeyHolder keyHolder = createCustomer.executeAndReturnKeyHolder(
				new BeanPropertySqlParameterSource(entity));
		return keyHolder.getKey().longValue();
	}
	
	
	@Override
	public void update(T entity) {
		String sql = String.format(getSQLUpdate(), this.nameTable);
		namedParameterJdbcTemplate.update(
				sql,
				new BeanPropertySqlParameterSource(entity));
	}
	
	@Override
	public void delete(Long id) {
		jdbcTemplate.update(
				"DELETE FROM " + this.nameTable +" WHERE id=?", 
				new Object[] { id });
	}

	@Override
	public List<T> getAll() {
		return jdbcTemplate.query(
				"SELECT * FROM " + this.nameTable, 
				new BeanPropertyRowMapper<T>(type));
	}

}
