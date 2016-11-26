package com.epam.training2016.aviacompany.web.utils;

import org.springframework.stereotype.Service;
import com.epam.training2016.aviacompany.datamodel.Team;
import com.epam.training2016.aviacompany.web.model.TeamModel;

@Service
public class TeamConverter extends BaseConverter<Team, TeamModel>{

	@Override
	public TeamModel entity2model(Team entity) {
		TeamModel model = new TeamModel();
		model.setPilot(entity.getPilot());
		model.setNavigator(entity.getNavigator());
		model.setRadioman(entity.getRadioman());
		model.setStewardess1(entity.getStewardess1());
		model.setStewardess2(entity.getStewardess2());

		return model;
	}

	@Override
	public Team model2entity(TeamModel model) {
		Team entity = new Team();
		entity.setPilot(model.getPilot());
		entity.setNavigator(model.getNavigator());
		entity.setRadioman(model.getRadioman());
		entity.setStewardess1(model.getStewardess1());
		entity.setStewardess2(model.getStewardess2());

		return entity;
	}

}
