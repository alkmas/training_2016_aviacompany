package com.epam.training2016.aviacompany.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.daoapi.ITeamDao;
import com.epam.training2016.aviacompany.daoapi.customentity.EmployeeWithTeam;
import com.epam.training2016.aviacompany.daodb.mapper.EmployeeWithTeamMapper;
import com.epam.training2016.aviacompany.daodb.util.CacheDao;
import com.epam.training2016.aviacompany.datamodel.Team;

@Repository
public class TeamDaoImpl extends BaseDaoImpl<Team> implements ITeamDao{
	final private String SQL_UPDATE_BY_ID = 
			"UPDATE team SET pilot=:pilot, navigator=:navigator, radioman=:radioman," 
			+ " stewardess1=:stewardess1,stewardess2=:stewardess2 WHERE id=:id";

	private String SQL_SELECT_EMPLOYEE_WITH_TEAM_ID = 
			"SELECT e.id, e.first_name, e.last_name, e.birthday, e.job_title_id, t.id AS team_id"
			+ " FROM employee e LEFT JOIN team t ON t.pilot = e.id OR t.navigator = e.id"
			+ " OR t.radioman = e.id  OR t.stewardess1 = e.id  OR t.stewardess2 = e.id"; 
	
	private String SQL_SELECT_EMPLOYEE_WITH_TEAM_ID_BY_ID =
			SQL_SELECT_EMPLOYEE_WITH_TEAM_ID + " WHERE e.id=?";
	
	private String SQL_SELECT_EMPLOYEE_WITH_TEAM_ID_BY_JOB_ID =
			SQL_SELECT_EMPLOYEE_WITH_TEAM_ID + " WHERE e.job_title_id=?";

	@Inject
	private CacheDao<EmployeeWithTeam> cache;

	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}

	@Override
	public EmployeeWithTeam getEmployeeWithTeamById(Long id) {
		return cache.cacheEntity(
				() -> jdbcTemplate.queryForObject(SQL_SELECT_EMPLOYEE_WITH_TEAM_ID_BY_ID,
						new Object[] { id }, new EmployeeWithTeamMapper()),
				"employee", id);
	}

	@Override
	public List<EmployeeWithTeam> getAllEmployeeWithTeam() {
		return cache.cacheList(
				() -> jdbcTemplate.query(SQL_SELECT_EMPLOYEE_WITH_TEAM_ID, 
						new EmployeeWithTeamMapper()),
				"getAllEmployeeWithTeam");
	}

	@Override
	public List<EmployeeWithTeam> getAllEmployeeWithTeamByJobId(Long jobId) {
		return cache.cacheList(
				() -> jdbcTemplate.query(SQL_SELECT_EMPLOYEE_WITH_TEAM_ID_BY_JOB_ID,
						new Object[] {jobId}, new EmployeeWithTeamMapper()),
				"getAllEmployeeWithTeamByJobId");
	}

	@Override
	public Class<Team> getGenericTypeClass() {
		return Team.class;
	}

}
