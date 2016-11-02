package com.epam.training2016.aviacompany.services;

import java.sql.Date;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class EmployeetServiceTest {
    @Inject
    private EmployeeService employeeService;
    @Inject
    private JobTitleService jobtitleService;

    
    @Test
    public void getByIdTest() {
    	EmployeeWithJobtitle employee = employeeService.getWithJobtitle(1L);
    	System.out.println(employee);
    }
    
    
    @Test
    @Transactional
    public void createEmployeeTest() {
    	
    	Employee employee = new Employee();
    	employee.setFirstName("Дарья");
    	employee.setLastName("Иванова");
    	employee.setBirthday(Date.valueOf("1980-04-03"));
    	
    	JobTitle jobtitle = jobtitleService.getByName("Стюард").get(0);
    	
    	employee.setJobTitleId(jobtitle.getId());
    	employeeService.save(employee);
    	Long id = employee.getId();
    			
    	Employee employeeFromBase = employeeService.getById(id);
    	
    	employee.setFirstName("Анна");
    	Assert.assertTrue("Employees are equals!", employee.equals(employeeFromBase));
   	
    }

    
}
