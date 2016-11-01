package com.epam.training2016.aviacompany.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.epam.training2016.aviacompany.datamodel.Employee;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.traininng2016.aviacompany.daodb.customentity.EmployeeWithJobtitle;


public final class EmployeeWithJobTitleMapper implements
        RowMapper<EmployeeWithJobtitle> {
    @Override
    public EmployeeWithJobtitle mapRow(ResultSet rs, int rowNum)
            throws SQLException {
    	Employee employee = new Employee();
    	employee.setId(rs.getLong(1));
    	employee.setFirstName(rs.getString(2));
    	employee.setLastName(rs.getString(3));
    	employee.setBirthday(rs.getDate(4));
    	employee.setJobTitleId(rs.getLong(5));
    	
    	JobTitle jobtitle = new JobTitle();
    	jobtitle.setId(rs.getLong(6));
    	jobtitle.setName(rs.getString(7));
    	
    	EmployeeWithJobtitle employeeWithJobtitle = new EmployeeWithJobtitle();
    	employeeWithJobtitle.setEmployee(employee);
    	employeeWithJobtitle.setJobtitle(jobtitle);
    	
        return employeeWithJobtitle;
    }
}