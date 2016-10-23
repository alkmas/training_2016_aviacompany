package com.epam.training2016.aviacompany.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.epam.training2016.aviacompany.datamodel.Airport;


public final class AirportMapper implements RowMapper<Airport> {
    @Override
    public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Airport entity = new Airport();
        entity.setId(id);
        entity.setName(name);
        return entity;
    }
}