package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.IBaseDao;

@Repository
public interface BaseDao<T> extends IBaseDao<T> {
//	T getById(Long id);
	T getById(Long id, RowMapper<T> rowMapper);
//	T getByName(String name);
//	Long insert(T entity);
	Long insert(T entity, SqlParameterSource parameterSource);
//	void update(T entity);
	void update(T entity, SqlParameterSource parameterSource);
//	void deleteById(Long id);
//	List<T> getAll();
	List<T> getAll(RowMapper<T> rowMapper);
}
