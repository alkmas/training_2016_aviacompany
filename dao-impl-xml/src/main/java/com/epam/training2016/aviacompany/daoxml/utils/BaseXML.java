package com.epam.training2016.aviacompany.daoxml.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.thoughtworks.xstream.XStream;


public class BaseXML<T> implements GenericType<T> {
	private Class<T> clazz;
	private String shortNameClass;
    private XStream xstream;
    private File file;
    private List<T> baseList;
    private String basePath;
    
    private void initialize() {
        xstream = new XStream();
        xstream.alias(shortNameClass, clazz);
        
        try (InputStream inputStream = 
        		getClass().getClassLoader().getResourceAsStream("xml-db.properties")) {
        	Properties properties = new Properties();
        	properties.load(inputStream);
        	basePath = properties.getProperty("basePath");
		} catch (IOException e) {
			e.printStackTrace();
		}
        file = new File(basePath + "/" + shortNameClass + ".xml");
        try {
			if (!file.exists()) {
			    file.createNewFile();
			    xstream.toXML(new ArrayList<>(), new FileOutputStream(
			            file));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		baseList = this.readCollection();
    }

	public BaseXML(Class<T> clazz) {
		this.clazz = clazz;
		shortNameClass = clazz.getSimpleName();
		initialize();
	}

	public List<T> getBaseList() {
		return baseList;
	}


	public void setBaseList(List<T> newList) {
		this.baseList = newList;
		writeCollection();
	}

	// Возвращает id + 1 последнего объекта. 
    public Long getNextId() {
    	if (baseList.isEmpty()) return 1L;
    	// сортируем перед получением следующего id
    	sortBaseListById();
    	T entity = baseList.get(baseList.size() - 1);
       	Long id = (Long) getValueField(entity, "id");
       	return id + 1;
    }


    public Object getValueField(T entity, String fieldName) {
		String newName = "get" + StringUtils.capitalize(fieldName);
    	try {
    		return clazz.getMethod(newName).invoke(entity, new Object[] {});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

    public void setId(T entity, Long id) {
    	try {
    		clazz.getMethod("setId", Long.class).invoke(entity, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    
    
    /**
     * Удаление объекта по полю класса Long
     * @param fieldName
     * @param id
     */
	public void deleteByField(String fieldName, Long id) {
		List<T> resultList = new ArrayList<T>();
		Long idGet;
		for(T entity: baseList) {
			idGet = (Long) getValueField(entity, fieldName);
			if (idGet.equals(id)) continue;
			resultList.add(entity);
		}
		this.setBaseList(resultList);
	}

	public int indexOf(Object entity) {
		return baseList.indexOf(entity);
	}


	public void addEntity(T entity) {
		baseList.add(entity);
		writeCollection();
	}

	public void findAndReplace(T entity, T newEntity) {
		int index = baseList.indexOf(entity);
		if (index != -1) {
			baseList.set(index, newEntity);
			writeCollection();
		}
	}


	private void sortBaseListById() {
    	baseList.sort(new Comparator<T>(){
    		public int compare(T entity1, T entity2) {
    			Long idEntity1 = (Long) getValueField(entity1, "id");
    			Long idEntity2 = (Long) getValueField(entity2, "id");
    			if (idEntity1 > idEntity2) {
    				return 1;
    			} else if (idEntity1 < idEntity2) {
    				return -1;
    			} else {
    				return 0;
    			}
    		}
    	});
	}

	@SuppressWarnings("unchecked")
    public List<T> readCollection() {
        return (List<T>) xstream.fromXML(file);
    }

    public void writeCollection() {
        try {
            xstream.toXML(baseList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public Class<T> getGenericType() {
		// TODO Auto-generated method stub
		return null;
	}


}
