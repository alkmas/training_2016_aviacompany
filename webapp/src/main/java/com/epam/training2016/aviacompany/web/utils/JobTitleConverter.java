package com.epam.training2016.aviacompany.web.utils;

import org.springframework.stereotype.Service;
import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.web.model.JobTitleModel;

@Service
public class JobTitleConverter extends BaseConverter<JobTitle, JobTitleModel>{

	@Override
	public JobTitleModel entity2model(JobTitle entity) {
		JobTitleModel model = new JobTitleModel();
		model.setName(entity.getName());
		return model;
	}

	@Override
	public JobTitle model2entity(JobTitleModel model) {
		JobTitle entity = new JobTitle();
		entity.setName(model.getName());
		return entity;
	}

}
