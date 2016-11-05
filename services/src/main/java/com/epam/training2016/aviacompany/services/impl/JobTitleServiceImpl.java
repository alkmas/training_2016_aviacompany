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
import com.epam.training2016.aviacompany.services.EmployeeService;
import com.epam.training2016.aviacompany.services.JobTitleService;
import com.epam.training2016.aviacompany.services.utils.IdNullException;

@Service
public class JobTitleServiceImpl implements JobTitleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTitleServiceImpl.class);
	@Inject
	private JobTitleDaoImpl jobtitleDao;
	@Inject
	private EmployeeService employeeService;

	@Override
	public void saveAll(List<JobTitle> entities) {
		for(JobTitle entity: entities) {
			save(entity);
		}
	}

	@Override
	public void save(JobTitle entity) {
		if (entity.getId() == null) {
			try {
				entity.setId(jobtitleDao.insert(entity));				
			}
			catch (DuplicateKeyException e) {
				LOGGER.error(String.format("Duplacate Name: %s", entity.getName()));
			}

		} else {
			jobtitleDao.update(entity);
		}
	}

	@Override
	public boolean isDaoExist() {
		return jobtitleDao != null;
	}

	@Override
	public JobTitle getById(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
		return jobtitleDao.getById(id);
	}

	@Override
	public List<JobTitle> getAll() {
		return jobtitleDao.getAll();
	}


	@Override
	public void deleteById(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
		String jobString = jobtitleDao.getById(id).toString();
		jobtitleDao.deleteById(id);
		LOGGER.info(String.format("Deleted (%s) from table Jobtitle", jobString));
	}

	@Override
	@Transactional
	public void deleteCascadeById(Long id) throws IdNullException {
		IdNullException.CheckIdParameter(id);
		employeeService.deleteByJobTitleId(id);
		deleteById(id);
	}

	@Override
	public JobTitle getByName(String name) {
		return jobtitleDao.getByName(name);
	}

	@Override
	public List<JobTitle> filter(JobTitle entityFilter) {
		List<JobTitle> resultList = new ArrayList<JobTitle>(); 
		for(JobTitle jobtitle: jobtitleDao.getAll()) {
			if (jobtitle.filter(entityFilter)) {
				resultList.add(jobtitle);
			}
		}
		return resultList;
	}

}
