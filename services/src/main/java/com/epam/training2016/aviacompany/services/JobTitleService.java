package com.epam.training2016.aviacompany.services;

import com.epam.training2016.aviacompany.datamodel.JobTitle;

public interface JobTitleService extends BaseService<JobTitle> {
	JobTitle getByName(String value);
}
