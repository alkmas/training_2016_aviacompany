package com.epam.training2016.aviacompany.daoxml.utils;

import java.util.List;

public interface IBaseXML<T> {
	List<T> getBaseList();
	void setBaseList(List<T> baseList);
	Object getValueField(T entity, String fieldName);
	Long getNextId();
	void deleteByField(String fieldName, Long id);
	int indexOf(T entity);
	void setByIndex(int index, T entity);
	void addEntity(T entity);
	boolean findAndReplace(T entity);
	List<T> readCollection();
	void writeCollection();
}
