package com.epam.training2016.aviacompany.daoxml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import com.epam.training2016.aviacompany.daoapi.IBaseDao;
import com.thoughtworks.xstream.XStream;

public class BaseDaoXmlImpl<T> implements IBaseDao<T> {
	private Class<T> genericClass;
	private String genericNameClass;
	private String shortNameClass;
	
    private XStream xstream;
    private File file;

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
    }


	BaseDaoXmlImpl() {
		setGenericNameAndTypeClass();
		shortNameClass = genericNameClass.substring(genericNameClass.lastIndexOf('.') + 1);
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
	
	
    private long getNextId(List<T> entities) throws Exception {
    	T entity = entities.get(entities.size() - 1);
    	Long id = (Long) genericClass.getMethod("getId").invoke(entity, new Object[] {});
        return entities.isEmpty() ? 1l : id + 1;
    }

    private List<T> readCollection() {
        return (List<T>) xstream.fromXML(file);
    }

    private void writeCollection(List<T> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
	public Long insert(T entity) throws Exception {
        List<T> list = readCollection();
        Long id = getNextId(list);

        list.add(entity);
        genericClass.getMethod("setId", Long.class).invoke(entity, id);
        
//        entity.setId(new Long(id));

        writeCollection(list);
        return id;
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
