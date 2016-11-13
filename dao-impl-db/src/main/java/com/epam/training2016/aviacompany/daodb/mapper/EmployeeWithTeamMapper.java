package com.epam.training2016.aviacompany.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithTeam;


public final class EmployeeWithTeamMapper implements
        RowMapper<EmployeeWithTeam> {
    @Override
    public EmployeeWithTeam mapRow(ResultSet rs, int rowNum)
            throws SQLException {
    	Employee employee = new Employee();
    	employee.setId(rs.getLong("id"));
    	employee.setFirstName(rs.getString("first_name"));
    	employee.setLastName(rs.getString("last_name"));
    	employee.setBirthday(rs.getDate("birthday"));
    	employee.setJobTitleId(rs.getLong("job_title_id"));
    	
    	EmployeeWithTeam employeeWithTeam = new EmployeeWithTeam();
    	employeeWithTeam.setEmployee(employee);
    	Long teamId = rs.getLong("team_id");
    	employeeWithTeam.setTeamId(teamId == 0L? null: teamId);
    	
        return employeeWithTeam;
    }
}