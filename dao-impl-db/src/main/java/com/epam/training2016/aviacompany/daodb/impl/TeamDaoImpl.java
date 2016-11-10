package com.epam.training2016.aviacompany.daodb.impl;

import org.springframework.stereotype.Repository;

import com.epam.training2016.aviacompany.datamodel.Team;

@Repository
public class TeamDaoImpl extends BaseDaoImpl<Team>{
	final private String SQL_UPDATE_BY_ID = 
			"UPDATE team SET pilot=:pilot, navigator=:navigator, radioman=:radioman," 
			+ " stewardess1=:stewardess1,stewardess2=:stewardess2 WHERE id=:id";

	protected String getStringSQLUpdate() {
		return SQL_UPDATE_BY_ID;
	}


}
