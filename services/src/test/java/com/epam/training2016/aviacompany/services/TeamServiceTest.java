package com.epam.training2016.aviacompany.services;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.Team;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;
import com.epam.training2016.aviacompany.services.impl.BaseServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class TeamServiceTest {
    @Inject
    private BaseServiceImpl<Team> teamService;
    @Inject
    private EmployeeService employeeService;

    @Test
    public void teamNotExistTest() {
    	Team team = teamService.getById(1000L);
    	System.out.println(team);
        Assert.assertNull("team for id=1000L not exist", team);
    }
    
    @Test
    public void insertTest() throws InvalidDataException {
    	Team team = new Team();
    	team.setPilot(employeeService.getById(1L).getId());
    	team.setNavigator(employeeService.getById(1L).getId());
    	team.setRadioman(employeeService.getById(1L).getId());
    	team.setStewardess1(employeeService.getById(1L).getId());
    	team.setStewardess2(employeeService.getById(1L).getId());
    	teamService.save(team);
    }
    
}
