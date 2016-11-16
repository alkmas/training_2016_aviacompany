package com.epam.training2016.aviacompany.daoxml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.epam.training2016.aviacompany.daoapi.IBaseDao;
import com.thoughtworks.xstream.XStream;

public class BaseDaoXmlImpl<T> implements IBaseDao<T> {
	private Class<T> genericClass;
	private String genericNameClass;
	private String shortNameClass;
    private XStream xstream;
    private File file;
    private List<T> baseList;

    @Value("${basePath}")
    private String basePath;

    @PostConstruct
    private void intialize() throws IOException {
        xstream = new XStream();
        xstream.alias(shortNameClass, genericClass);

        file = new File(basePath + "/" + shortNameClass + ".xml");
        if (!file.exists()) {
            file.createNewFile();
            xstream.toXML(new ArrayList<>(), new FileOutputStream(
                    file));
        }
        
		baseList = this.readCollection();
    }


	BaseDaoXmlImpl() {
		setGenericNameAndTypeClass();
		shortNameClass = genericNameClass.substring(genericNameClass.lastIndexOf('.') + 1);
	}
	
	public List<T> getBaseList() {
		return baseList;
	}

	public void setBaseList(List<T> baseList) {
		this.baseList = baseList;
		this.writeCollection(baseList);
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
	
	
    private Long getNextId(List<T> entities) {
    	if (entities.isEmpty()) return 1L;
    	T entity = entities.get(entities.size() - 1);
       	Long id = (Long) getField(entity, "getId");
       	return id + 1;
    }

    
    protected Object getField(T entity, String field) {
    	try {
    		return genericClass.getMethod(field).invoke(entity, new Object[] {});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

    
	@SuppressWarnings("unchecked")
    protected List<T> readCollection() {
        return (List<T>) xstream.fromXML(file);
    }

    
    protected void writeCollection(List<T> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление объекта по полю класса Long
     * @param fieldName
     * @param id
     */
	protected void deleteByField(String fieldName, Long id) {
		List<T> resultList = new ArrayList<T>();
		Long idGet;
		fieldName = "get" + StringUtils.capitalize(fieldName);
		for(T entity: baseList) {
			idGet = (Long) getField(entity, fieldName);
			if (idGet == id) continue;
			resultList.add(entity);
		}
		this.setBaseList(resultList);
	}

	
	@Override
	public T getById(Long id) {
		Long idGet;
		for(T entity: baseList) {
			idGet = (Long) getField(entity, "getId");
			if (idGet == id) return entity;
		}
		return null;
	}

	
	@Override
	public T getByName(String name) {
		String nameGet;
		for(T entity: baseList) {
			nameGet = (String) getField(entity, "getName");
			if (nameGet.equals(name)) return entity;
		}
		return null;
	}

	
	@Override
	public Long insert(T entity) {
        Long id = getNextId(baseList);
		try {
			genericClass.getMethod("setId", Long.class).invoke(entity, id);
			baseList.add(entity);
	        writeCollection(baseList);
	        return id;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(T entity) {
		Long id = (Long) getField(entity, "getId");
		List<T> resultList = new ArrayList<T>();
		Long idGet;
		for(T entityGet: baseList) {
			idGet = (Long) getField(entity, "getId");
			if (idGet == id) {
				resultList.add(entity);
			} else {
				resultList.add(entityGet);
			}
		}
		this.setBaseList(resultList);
	}

	@Override
	public void deleteById(Long id) {
		deleteByField("id", id);
	}

	@Override
	public List<T> getAll() {
        return baseList;
	}
	

}
