package com.epam.training2016.aviacompany.services.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import com.epam.training2016.aviacompany.daoapi.IBaseDao;
import com.epam.training2016.aviacompany.services.BaseService;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;

public class BaseServiceImpl<T> implements BaseService<T> {
    private Logger LOGGER;
	private Class<T> genericClass;
	private String genericNameClass;
	
    @Inject
//	private BaseDaoImpl<T> baseDao;
    private IBaseDao<T> baseDao;
    
	public BaseServiceImpl() {
		setGenericTypeClass();
		LOGGER = LoggerFactory.getLogger(genericClass);
	}


	// Установка generic класса
	@SuppressWarnings("unchecked")
    private void setGenericTypeClass() {
        try {
            this.genericNameClass = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            Class<?> clazz = Class.forName(this.genericNameClass);
            this.genericClass = (Class<T>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
        }
    } 
    
	
	@Override
	public void saveAll(List<T> entities) throws InvalidDataException {
        for (T entity : entities) {
            save(entity);
        }
	}

	@Override
	public void save(T entity) throws InvalidDataException {
		try {
			Long id = (Long) genericClass.getMethod("getId").invoke(entity, new Object[] {});
			if (id == null) {
				id = baseDao.insert(entity);
				genericClass.getMethod("setId", Long.class).invoke(entity, id);
			} else {
				baseDao.update(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteById(Long id) {
		if (id != null) {
			String delRecord = this.getById(id).toString();
			baseDao.deleteById(id);
			LOGGER.info(String.format("Deleted (%s) from table (%s)", delRecord, this.genericNameClass));
		}
		else {
			LOGGER.info("Method: deleteById. Input parameter is null");
		}
	}

	@Override
	public boolean isDaoExist() {
		return baseDao != null;
	}

	@Override
	public T getById(Long id) {
		try {
			return baseDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public T getByName(String name) {
		try {
			return baseDao.getByName(name);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<T> getAll() {
		return baseDao.getAll();
	}
}
