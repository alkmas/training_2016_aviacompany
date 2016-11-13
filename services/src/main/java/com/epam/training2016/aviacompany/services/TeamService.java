package com.epam.training2016.aviacompany.services;

import java.util.Map;
import com.epam.training2016.aviacompany.datamodel.Team;

public interface TeamService extends BaseService<Team> {
	Map<Long, Boolean> getFreeEmployeesByJobtitle(Long jobId);
}
