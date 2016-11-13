package com.epam.training2016.aviacompany.daoxml.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.epam.training2016.aviacompany.daoapi.IBaseDao;

public class BaseDaoXmlImpl<T> implements IBaseDao<T> {
	private Class<T> genericClass;
	private String genericNameClass;
	private String nameFile;

	BaseDaoXmlImpl() {
		setGenericNameAndTypeClass();
		nameFile = genericNameClass.substring(genericNameClass.lastIndexOf('.') + 1);
	}
	
	// Установка generic класса и его имени
	@SuppressWarnings("unchecked")
    private void setGenericNameAndTypeClass() {
        try {
            this.genericNameClass = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            Class<?> clazz = Class.forName(this.genericNameClass);
            this.genericClass = (Class<T>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
        }
    } 
	
	@Override
	public T getById(Long id) {
        throw new UnsupportedOperationException();
	}

	@Override
	public T getByName(String name) {
        throw new UnsupportedOperationException();
	}

	@Override
	public Long insert(T entity) {
        throw new UnsupportedOperationException();
	}

	@Override
	public void update(T entity) {
        throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Long id) {
        throw new UnsupportedOperationException();
	}

	@Override
	public List<T> getAll() {
        throw new UnsupportedOperationException();
	}
	

}
