package com.epam.training2016.aviacompany.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.training2016.aviacompany.datamodel.Team;
import com.epam.training2016.aviacompany.services.exceptions.InvalidDataException;
import com.epam.training2016.aviacompany.services.impl.BaseServiceImpl;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-test.xml")
public class TeamServiceTest {
    @Inject
    private TeamService teamService;
    @Inject
    private EmployeeService employeeService;

    @Test
    public void teamNotExistTest() {
    	System.out.println("-------------teamNotExistTest--------------");
    	Team team = teamService.getById(1000L);
    	System.out.println(team);
        Assert.assertNull("team for id=1000L not exist", team);
    }
    
    @Test (expected = InvalidDataException.class)
    public void insertInvalidDataTest() throws InvalidDataException {
    	System.out.println("-------------insertTest--------------");
    	Team team = new Team();
    	team.setPilot(employeeService.getById(1L).getId());
    	team.setNavigator(employeeService.getById(1L).getId());
    	team.setRadioman(employeeService.getById(1L).getId());
    	team.setStewardess1(employeeService.getById(1L).getId());
    	team.setStewardess2(employeeService.getById(1L).getId());
    	teamService.save(team);
    }
     
    
    @Test
    public void createTeamTest() throws InvalidDataException {
    	System.out.println("-------------createTeamTest--------------");
    	EmployeeWithTeam pilot = teamService.getAllEmployeeWithTeamFreeByJobName("Пилот").get(0);
    	EmployeeWithTeam navigator = teamService.getAllEmployeeWithTeamFreeByJobName("Штурман").get(0);
    	EmployeeWithTeam radioman = teamService.getAllEmployeeWithTeamFreeByJobName("Радист").get(0);
    	List<EmployeeWithTeam> stewardesses = teamService.getAllEmployeeWithTeamFreeByJobName("Стюардесса");
    	EmployeeWithTeam stewardess1 = stewardesses.get(0);
    	EmployeeWithTeam stewardess2 = stewardesses.get(1);

    	Team newTeam = new Team();
    	newTeam.setPilot(pilot.getEmployee().getId());
    	newTeam.setNavigator(navigator.getEmployee().getId());
    	newTeam.setRadioman(radioman.getEmployee().getId());
    	newTeam.setStewardess1(stewardess1.getEmployee().getId());
    	newTeam.setStewardess2(stewardess2.getEmployee().getId());
    	teamService.save(newTeam);
    	
    	// Проверка
    	Team teamFromBase = teamService.getById(newTeam.getId());
    	Assert.assertNotNull(teamFromBase);
    	
    	// Проверка на удаление
    	teamService.deleteById(teamFromBase.getId());
    	teamFromBase = teamService.getById(teamFromBase.getId());
    	Assert.assertNull(teamFromBase);
    }
    
    
    @Test
    public void getAllWhoHaveTeamTest() {
    	System.out.println("-------------getAllWhoHaveTeamTest--------------");
    	for(EmployeeWithTeam emp: teamService.getAllEmployeeWithTeam()) {
    		if (emp.getTeamId() != null) {
    			System.out.println(emp);
    		}
    	}
    }
    
    @Test
    public void getPilots() {
    	System.out.println("-------------getPilots--------------");
    	for(EmployeeWithTeam emp: teamService.getAllEmployeeWithTeamByJobName("Пилот")) {
    		System.out.println(emp.getEmployee().getLastName() +
    				"->" + (emp.getTeamId()==null? "Свободен" : "Занят"));
    	}
    }

    
    
}
