package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daodb.BaseDao;
import com.epam.training2016.aviacompany.datamodel.JobTitle;

public class JobTitleDaoImpl extends BaseDaoImpl<JobTitle> {

	JobTitleDaoImpl() {
		super(JobTitle.class);
	}
}
