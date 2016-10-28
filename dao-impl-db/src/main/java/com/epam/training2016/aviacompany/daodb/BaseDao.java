package com.epam.training2016.aviacompany.daodb;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseDao<T> {
	T get(Long id);
	Long insert(T entity);
	void updateField(T entity, String field);
	void updateAllField(T entity);
	void delete(Long id);
	List<T> getAll();
}
