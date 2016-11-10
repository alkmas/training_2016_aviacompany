package com.epam.training2016.aviacompany.services.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training2016.aviacompany.daodb.impl.BaseDaoImpl;
import com.epam.training2016.aviacompany.datamodel.FlightDays;
import com.epam.training2016.aviacompany.services.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {
    private Logger LOGGER;
	private Class<T> genericClass;
	private String genericNameClass;
	
    @Inject
	private BaseDaoImpl<T> baseDao;
	
    
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
	public void saveAll(List<T> entities) {
        for (T entity : entities) {
            save(entity);
        }
	}

	@Override
	// !!!! обработать исключение !!!
	public void save(T entity) {
		try {
			Long id = (Long) genericClass.getMethod("getId").invoke(entity, new Object[] {});
			if (id == null) {
				genericClass.getMethod("setId", Long.class).invoke(entity, baseDao.insert(entity));
			} else {
				baseDao.update(entity);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void deleteById(Long id) {
		String delRecord = this.getById(id).toString();
		baseDao.deleteById(id);
		LOGGER.info(String.format("Deleted (%s) from table (%s)", delRecord, this.genericNameClass));
	}

	@Override
	public boolean isDaoExist() {
		return baseDao != null;
	}

	@Override
	public T getById(Long id) {
		return baseDao.getById(id);
	}

	@Override
	public T getByName(String name) {
		return baseDao.getByName(name);
	}

	@Override
	public List<T> getAll() {
		return baseDao.getAll();
	}
}
