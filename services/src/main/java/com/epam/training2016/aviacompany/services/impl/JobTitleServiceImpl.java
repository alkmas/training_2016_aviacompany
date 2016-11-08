package com.epam.training2016.aviacompany.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.daodb.impl.JobTitleDaoImpl;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.services.JobTitleService;

@Service
public class JobTitleServiceImpl implements JobTitleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTitleServiceImpl.class);
	@Inject
	private JobTitleDaoImpl jobtitleDao;


	@Override
	@Transactional
	public void saveAll(List<JobTitle> entities) {
		for(JobTitle entity: entities) {
			save(entity);
		}
	}

	@Override
	public void save(JobTitle entity) {
		if (entity.getId() == null) {
			entity.setId(jobtitleDao.insert(entity));				
		} else {
			jobtitleDao.update(entity);
		}
	}

	@Override
	public boolean isDaoExist() {
		return jobtitleDao != null;
	}

	@Override
	public JobTitle getById(Long id) {
		return jobtitleDao.getById(id);
	}

	@Override
	public List<JobTitle> getAll() {
		return jobtitleDao.getAll();
	}


	@Override
	public void deleteById(Long id) {
		String jobString = jobtitleDao.getById(id).toString();
		jobtitleDao.deleteById(id);
		LOGGER.info(String.format("Deleted (%s) from table Jobtitle", jobString));
	}

	@Override
	public JobTitle getByName(String name) {
		return jobtitleDao.getByName(name);
	}

}
