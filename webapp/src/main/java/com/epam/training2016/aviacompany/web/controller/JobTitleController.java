package com.epam.training2016.aviacompany.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training2016.aviacompany.datamodel.JobTitle;
import com.epam.training2016.aviacompany.web.model.JobTitleModel;

@RestController
@RequestMapping("/jobtitle")
public class JobTitleController extends BaseController<JobTitle, JobTitleModel> {

}
