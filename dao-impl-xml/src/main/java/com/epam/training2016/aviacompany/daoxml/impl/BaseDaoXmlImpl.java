package com.epam.training2016.aviacompany.daoxml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Comparator;
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
		this.writeCollection();
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
	
	
	// Возвращает id + 1 последнего объекта. 
    private Long getNextId() {
    	if (baseList.isEmpty()) return 1L;
    	// сортируем перед получением следующего id
    	sortBaseListById();
    	T entity = baseList.get(baseList.size() - 1);
       	Long id = (Long) getValueField(entity, "id");
       	return id + 1;
    }

    
    protected Object getValueField(T entity, String fieldName) {
		String newName = "get" + StringUtils.capitalize(fieldName);
    	try {
    		return genericClass.getMethod(newName).invoke(entity, new Object[] {});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

    
	@SuppressWarnings("unchecked")
    protected List<T> readCollection() {
        return (List<T>) xstream.fromXML(file);
    }

    
    protected void writeCollection() {
        try {
            xstream.toXML(baseList, new FileOutputStream(file));
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
		for(T entity: baseList) {
			idGet = (Long) getValueField(entity, fieldName);
			if (idGet == id) continue;
			resultList.add(entity);
		}
		this.setBaseList(resultList);
	}

	
	@Override
	public T getById(Long id) {
		Long idGet;
		for(T entity: baseList) {
			idGet = (Long) getValueField(entity, "id");
			if (idGet.equals(id)) return entity;
		}
		return null;
	}

	
	@Override
	public T getByName(String name) {
		String nameGet;
		for(T entity: baseList) {
			nameGet = (String) getValueField(entity, "name");
			if (nameGet.equals(name)) return entity;
		}
		return null;
	}

	
	@Override
	public Long insert(T entity) {
		try {
			Long id = (Long) getValueField(entity, "id");
			if (id == null) {
				id = getNextId();
				genericClass.getMethod("setId", Long.class).invoke(entity, id);
			}
			baseList.add(entity);
	        writeCollection();
	        return id;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(T entity) {
		Long id = (Long) getValueField(entity, "id");
		boolean flagFound = false;
		Long idGet;
		for(int i=0; i<baseList.size(); i++) {
			idGet = (Long) getValueField(baseList.get(0), "id");
			if (idGet.equals(id)) {
				// поменять объект если id совпадают
				baseList.set(i, baseList.get(0));
				flagFound = true;
				break;
			}
		}
		if (flagFound) {
			writeCollection();
		} else {
			// если объект с id не найден то вставить его
			insert(entity);
		}
		
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
