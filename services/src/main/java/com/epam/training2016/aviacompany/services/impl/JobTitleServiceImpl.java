package com.epam.training2016.aviacompany.services.impl;

import org.springframework.stereotype.Service;

import com.epam.training2016.aviacompany.datamodel.JobTitle;

@Service
public class JobTitleServiceImpl extends BaseServiceImpl<JobTitle> {

	@Override
	public Class<JobTitle> getGenericTypeClass() {
		return JobTitle.class;
	}

}
