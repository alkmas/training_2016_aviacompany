package com.epam.training2016.aviacompany.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {
	private Class<T> type;
	@Inject
	protected JdbcTemplate jdbcTemplate;

	@Inject
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	BaseDaoImpl(Class<T> type) {
		this.type = type;
	}

	private String addNameTableInSQL(String sql) {
		return String.format(sql, type.getSimpleName());
	}

	/**
	 * Получаем строку из объекта в виде field1 = :field1, field2 = :field2, ...  
	 *@param entity
	 *@return
 	*/
	private String getStringFields(T entity) {
		String result = "";
		for(String property: new BeanPropertySqlParameterSource(entity).getReadablePropertyNames()) {
			if ((property.equals("class")) || (property.equals("id"))) continue;
			if (result.length() > 0) result += ",";
			result += property + "=:" + property;
		}
		return result;
	}
	
	
	@Override
	public T get(Long id) {
		String sql = addNameTableInSQL("SELECT * FROM %s WHERE id=?");
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<T>(type));
	}

	
	@Override
	public Long insert(T entity) {
		SimpleJdbcInsert createCustomer = new SimpleJdbcInsert(jdbcTemplate)
				   .withTableName(type.getSimpleName())
				   .usingGeneratedKeyColumns("id");
		KeyHolder keyHolder = createCustomer.executeAndReturnKeyHolder(new BeanPropertySqlParameterSource(entity));
		return keyHolder.getKey().longValue();
	}

	
	@Override
	public void updateField(T entity, String field) {
		String sql = addNameTableInSQL("UPDATE %s SET " + field + "=:" + field + " WHERE id=:id");
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity));
	}

	
	@Override
	public void updateAllField(T entity) {
		String sql = addNameTableInSQL("UPDATE %s SET " + getStringFields(entity) + " WHERE id=:id");
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity));
	}

	
	@Override
	public void delete(Long id) {
		jdbcTemplate.update(addNameTableInSQL("DELETE FROM %s WHERE id=?"), new Object[] { id });
	}

	@Override
	public List<T> getAll() {
		return jdbcTemplate.query(addNameTableInSQL("SELECT * FROM %s"), new BeanPropertyRowMapper<T>(type));
	}


}
