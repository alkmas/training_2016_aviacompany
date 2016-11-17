package com.epam.training2016.aviacompany.services;

import java.sql.Date;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.daoapi.customentity.EmployeeWithTeam;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-test.xml")
public class EmployeeServiceTest {
    @Inject
    private EmployeeService employeeService;
    @Inject
    private BaseService<JobTitle> jobtitleService;

    
    @Test
    public void createEmployeeTest() throws InvalidDataException {
    	
    	Employee employee = new Employee();
    	employee.setFirstName("Дарья");
    	employee.setLastName("Иванова");
    	employee.setBirthday(Date.valueOf("1980-04-03"));
    	
    	JobTitle jobtitle = jobtitleService.getByName("Стюардесса");
    	if (jobtitle != null) {
        	employee.setJobTitleId(jobtitle.getId());
    	}

    	employeeService.save(employee);
    	Long id = employee.getId();
    			
    	// Получить из базы сотрудника по id
    	Employee employeeFromBase = employeeService.getById(id);
    	
    	Assert.assertEquals("Employees are equals!", employee.getId(), employeeFromBase.getId());
    	
    	// Удалить и повторно получить из базы сотрудника по id
    	employeeService.deleteById(id);
    	employeeFromBase = employeeService.getById(id);
    	Assert.assertNull(employeeFromBase);
    }
    
    
}
