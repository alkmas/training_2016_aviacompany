package com.epam.training2016.aviacompany.daodb;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseDao<T> {
	T getByParams(Map<String,Object> params);
	T getById(Long id);
	T getByName(String name);
	Long insert(T entity);
	void update(T entity);
	void deleteById(Long id);
	List<T> getAll();
}
