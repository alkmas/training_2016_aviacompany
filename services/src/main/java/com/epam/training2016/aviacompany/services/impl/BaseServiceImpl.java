package com.epam.training2016.aviacompany.services.impl;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import com.epam.training2016.aviacompany.daoapi.IBaseDao;
import com.epam.training2016.aviacompany.datamodel.AbstractModel;
import com.epam.training2016.aviacompany.services.BaseService;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
    private Logger LOGGER;
	private Class<T> genericClass;
	private String genericNameClass;
	
    @Inject
    private IBaseDao<T> baseDao;
    
    protected abstract Class<T> getGenericTypeClass();
    
	public BaseServiceImpl() {
		genericClass = getGenericTypeClass();
		genericNameClass = genericClass.getSimpleName();
		LOGGER = LoggerFactory.getLogger(genericClass);
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
			AbstractModel model = (AbstractModel) entity;
			if (model.getId() == null) {
				model.setId(baseDao.insert(entity));
				LOGGER.info(String.format("Insert (%s) into (%s)", entity.toString(), genericNameClass));
			} else {
				baseDao.update(entity);
				LOGGER.info(String.format("Update (%s) into (%s)", entity.toString(), genericNameClass));
			}
		} catch (DuplicateKeyException e) {
			LOGGER.error(e.toString());
		}
	}

	@Override
	public void deleteById(Long id) {
		if (id != null) {
			String delRecord = this.getById(id).toString();
			baseDao.deleteById(id);
			LOGGER.info(String.format("Deleted (%s) from table (%s)", delRecord, genericNameClass));
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
