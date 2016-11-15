package com.epam.training2016.aviacompany.daoapi;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IBaseDao<T> {
	T getById(Long id);
	T getByName(String name);
	Long insert(T entity) throws Exception;
	void update(T entity);
	void deleteById(Long id);
	List<T> getAll();
}
