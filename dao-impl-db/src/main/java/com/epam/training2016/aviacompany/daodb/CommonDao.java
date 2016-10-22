package com.epam.training2016.aviacompany.daodb;

import java.util.List;

public interface CommonDao<T> {
	T get(Long id);
	void insert(T entity);
	void update(T entity);
	void delete(Long id);
	List<T> getAll();
}
