package com.epam.training2016.aviacompany.services;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.JobTitle;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class JobTitleServiceTest {
	private JobTitle jobtitle;
	@Inject
	private JobTitleService jobtitleService;
	
	@Before
	public void init() {
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
    	jobtitle = jobtitleService.getByName("МЕХАНИК");
    }

	@Test
    public void updateNameTest() {
    	jobtitle.setName("Mechanic");
    	jobtitleService.save(jobtitle);
    	JobTitle jobtitleFromBase = jobtitleService.getById(jobtitle.getId());
    	Assert.assertNotNull("Get jobtitle is not null", jobtitleFromBase);
    	Assert.assertEquals(jobtitleFromBase.getId(), jobtitle.getId());
    }
    
}
