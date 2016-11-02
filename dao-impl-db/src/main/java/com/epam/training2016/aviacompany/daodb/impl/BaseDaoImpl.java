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
	private String SQL_UPDATE_BY_ID = "UPDATE %s SET name=:name WHERE id=:id";
	private String SQL_SELECT_ALL = "SELECT * FROM %s";
	private String SQL_SELECT_BY_ID = "SELECT * FROM %s  WHERE id=?";
	private String SQL_SELECT_BY_NAME = "SELECT * FROM %s  WHERE name=?";
	private String SQL_DELETE_BY_ID = "DELETE FROM %s WHERE id=?";

	@Inject
	protected JdbcTemplate jdbcTemplate;
	@Inject
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	BaseDaoImpl(Class<T> type, String nameTable) {
		this.type = type;
		this.nameTable = nameTable;
		SQL_UPDATE_BY_ID = String.format(SQL_UPDATE_BY_ID, nameTable);
		SQL_SELECT_ALL = String.format(SQL_SELECT_ALL, nameTable);
		SQL_SELECT_BY_ID = String.format(SQL_SELECT_BY_ID, nameTable);
		SQL_SELECT_BY_NAME = String.format(SQL_SELECT_BY_NAME, nameTable);
		SQL_DELETE_BY_ID = String.format(SQL_DELETE_BY_ID, nameTable);
	}

	/**
	 * Возвращает шаблон для UPDATE запроса 
	 * @return
	 */
	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}
	
	@Override
	public T getById(Long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, 
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
		namedParameterJdbcTemplate.update(getStringSQLUpdate(),
				new BeanPropertySqlParameterSource(entity));
	}
	
	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update(SQL_DELETE_BY_ID, new Object[] { id });
	}

	@Override
	public List<T> getAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL, new BeanPropertyRowMapper<T>(type));
	}

	@Override
	public List<T> getByName(String name) {
		return jdbcTemplate.query(SQL_SELECT_BY_NAME,
				new Object[] { name },
				new BeanPropertyRowMapper<T>(type));
	}

}
