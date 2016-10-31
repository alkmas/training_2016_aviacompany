package com.epam.training2016.aviacompany.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.daodb.impl.JobTitleDaoImpl;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.services.BaseService;

@Service
public class JobTitleServiceImpl implements BaseService<JobTitle>{
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTitleServiceImpl.class);
	@Inject
	private JobTitleDaoImpl jobtitleDao;

	@Override
	public void saveAll(List<JobTitle> entities) {
		for(JobTitle entity: entities) {
			save(entity);
		}
	}

	@Override
	public Long save(JobTitle entity) {
		if (entity.getId() == null) {
			return jobtitleDao.insert(entity);
		} else {
			jobtitleDao.update(entity);
			return entity.getId();
		}
	}

	@Override
	public boolean isDaoExist() {
		return jobtitleDao != null;
	}

	@Override
	public JobTitle get(Long id) {
		return jobtitleDao.get(id);
	}

	@Override
	public List<JobTitle> getAll() {
		return jobtitleDao.getAll();
	}

}
