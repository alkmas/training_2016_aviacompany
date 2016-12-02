package com.epam.training2016.aviacompany.daoapi;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IBaseDao<T> {
	T getById(Long id);
	T getByName(String name);
	Long insert(T entity);
	int update(T entity);
	int deleteById(Long id);
	List<T> getAll();
}
