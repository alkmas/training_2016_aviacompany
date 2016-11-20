package com.epam.training2016.aviacompany.daoxml.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.IBaseDao;
import com.epam.training2016.aviacompany.daoxml.utils.BaseXML;
import com.epam.training2016.aviacompany.daoxml.utils.GenericType;

public abstract class BaseDaoXmlImpl<T> implements IBaseDao<T>, GenericType<T> {
	private Class<T> genericClass;
    final public BaseXML<T> baseXML;

    BaseDaoXmlImpl () {
    	baseXML = new BaseXML<T>(getGenericType());
    }

    @Override
	public T getById(Long id) {
		Long idGet;
		for(T entity: baseXML.getBaseList()) {
			idGet = (Long) baseXML.getValueField(entity, "id");
			if (idGet.equals(id)) return entity;
		}
		return null;
	}


	@Override
	public T getByName(String name) {
		String nameGet;
		for(T entity: baseXML.getBaseList()) {
			nameGet = (String) baseXML.getValueField(entity, "name");
			if (nameGet.equals(name)) return entity;
		}
		return null;
	}

	
	@Override
	public Long insert(T entity) {
		try {
			Long id = (Long) baseXML.getValueField(entity, "id");
			
			if (id != null) {
				if (getById(id) != null) throw new DuplicateKeyException(entity.toString());
			} else {
				id = baseXML.getNextId();
				baseXML.setId(entity, id);
			}
			baseXML.addEntity(entity);
	        return id;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(T entity) {
		Long id = (Long) baseXML.getValueField(entity, "id");
		
		T foundEntity = getById(id);
		if (foundEntity != null) {
			// поменять объект если id совпадают
			baseXML.findAndReplace(foundEntity, entity);
		} else {
			// вставить в список если такого Id нет
			insert(entity);
		}
	}

	@Override
	public void deleteById(Long id) {
		baseXML.deleteByField("id", id);
	}

	@Override
	public List<T> getAll() {
        return baseXML.getBaseList();
	}

}
