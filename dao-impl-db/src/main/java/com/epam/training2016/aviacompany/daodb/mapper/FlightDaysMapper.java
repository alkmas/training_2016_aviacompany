package com.epam.training2016.aviacompany.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.epam.training2016.aviacompany.datamodel.FlightDays;

public class FlightDaysMapper implements RowMapper<FlightDays> {

	@Override
	public FlightDays mapRow(ResultSet rs, int rowNum) throws SQLException {
		FlightDays flightDays = new FlightDays();
		flightDays.setId(rs.getLong("id"));
		for(int i=1; i<=7; i++) {
			flightDays.setDay(i, rs.getBoolean("day"+i));
		}
        return flightDays;
	}

}
