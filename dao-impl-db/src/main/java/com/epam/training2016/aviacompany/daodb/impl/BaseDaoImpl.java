package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.epam.training2016.aviacompany.daoapi.IBaseDao;
import com.epam.training2016.aviacompany.daodb.io.SerializationDao;
import com.epam.training2016.aviacompany.daodb.util.CacheDao;
import com.epam.training2016.aviacompany.daodb.util.StringUtils;

public abstract class BaseDaoImpl<T> implements IBaseDao<T> {
	private Class<T> genericClass;
	private String genericNameClass;
	private String nameTable;
	private String SQL_UPDATE_BY_ID = "UPDATE %s SET name=:name WHERE id=:id";
	private String SQL_SELECT_ALL = "SELECT * FROM %s";
	private String SQL_SELECT_BY_ID = "SELECT * FROM %s WHERE id=?";
	private String SQL_SELECT_BY_NAME = "SELECT * FROM %s WHERE name=?";
	private String SQL_DELETE_BY_ID = "DELETE FROM %s WHERE id=?";

	public abstract Class<T> getGenericTypeClass();
	@Inject
	protected JdbcTemplate jdbcTemplate;
	@Inject
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Inject
	protected CacheDao<T> cache;

	
	BaseDaoImpl() {
		genericClass = getGenericTypeClass();
		genericNameClass = genericClass.getSimpleName();
		nameTable = StringUtils.toDbFormat(genericNameClass);
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
		return cache.cacheEntity(
				() -> getById(id, new BeanPropertyRowMapper<T>(genericClass)), 
				nameTable, id);
	}

	protected T getById(Long id, RowMapper<T> rowMapper) {
		try {
			return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[] { id }, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Long insert(T entity) {
		Long result = insert(entity, new BeanPropertySqlParameterSource(entity));
		cache.clearAll();
		return result;
	}

	protected Long insert(T entity, SqlParameterSource parameterSource) {
		SimpleJdbcInsert createCustomer = new SimpleJdbcInsert(jdbcTemplate).withTableName(this.nameTable)
				.usingGeneratedKeyColumns("id");
		Long newId = createCustomer.executeAndReturnKey(parameterSource).longValue();

		try {
			this.genericClass.getMethod("setId", Long.class).invoke(entity, newId);
		} catch (Exception e) {
		}
		return newId;
	}

	@Override
	public void update(T entity) {
		update(entity, new BeanPropertySqlParameterSource(entity));
		cache.clearAll();
	}

	protected void update(T entity, SqlParameterSource parameterSource) {
		namedParameterJdbcTemplate.update(getStringSQLUpdate(), parameterSource);
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update(SQL_DELETE_BY_ID, new Object[] { id });
		cache.clearAll();
	}

	@Override
	public List<T> getAll() {
		return cache.cacheList(
				() -> getAll(new BeanPropertyRowMapper<T>(genericClass)),
				nameTable + 's');
	}

	protected List<T> getAll(RowMapper<T> rowMapper) {
		return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
	}

	@Override
	public T getByName(String name) {
		return jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, new Object[] { name },
				new BeanPropertyRowMapper<T>(genericClass));
	}

}
