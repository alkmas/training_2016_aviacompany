package com.epam.training2016.aviacompany.services;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;
import com.epam.training2016.aviacompany.services.impl.BaseServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class JobTitleServiceTest {
	private JobTitle jobtitle;
	@Inject
	private BaseServiceImpl<JobTitle> jobtitleService;
	
	@Before
	public void init() throws InvalidDataException {
		jobtitle = new JobTitle();
		jobtitle.setName("Механик");
		jobtitleService.save(jobtitle);
	}
	
	@After
	public void close() {
		jobtitleService.deleteById(jobtitle.getId());
	}

	@Test(expected = EmptyResultDataAccessException.class)
    public void getByNameTest() {
		jobtitleService.getByName("МЕХАНИК");
    }

	@Test
    public void updateNameTest() throws InvalidDataException {
    	jobtitle.setName("Mechanic");
    	jobtitleService.save(jobtitle);
    	JobTitle jobtitleFromBase = jobtitleService.getById(jobtitle.getId());
    	Assert.assertNotNull("Got jobtitle is not null", jobtitleFromBase);
    	Assert.assertEquals(jobtitleFromBase.getId(), jobtitle.getId());
    }
	
	@Test
	public void insertDuplicateTest() throws InvalidDataException {
		
		JobTitle jobtitleFromBase = jobtitleService.getById(jobtitle.getId());
    	Assert.assertNotNull("Got jobtitle is not null", jobtitleFromBase);
    	Assert.assertEquals(jobtitleFromBase.getId(), jobtitle.getId());
		
    	JobTitle jobtitleNew = new JobTitle();
    	jobtitleNew.setName(jobtitle.getName());
		jobtitleService.save(jobtitleNew);
	}
}
