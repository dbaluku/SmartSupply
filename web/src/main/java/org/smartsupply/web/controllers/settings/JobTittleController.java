package org.smartsupply.web.controllers.settings;

import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.admin.JobTitle;
import org.smartsupply.web.MenuConstants;
import org.smartsupply.web.ViewNameConstants;
import org.smartsupply.web.WebConstants;
import org.smartsupply.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("job-titles")
public class JobTittleController extends BaseController<BaseService<JobTitle>, JobTitle> {

    @Autowired
    private BaseService<JobTitle> jobTitleService;

    @Override
    protected String viewName() {
        return ViewNameConstants.JOB_TITLE_VIEW;
    }

    @Override
    protected String formName() {
        return ViewNameConstants.JOB_TITLE_FORM;
    }

    @Override
    protected String objectKey() {
        return WebConstants.OBJECT_KEY;
    }

    @Override
    protected String listKey() {
        return WebConstants.LIST_ITEMS;
    }

    @Override
    protected BaseService<JobTitle> getService() {
        return jobTitleService;
    }

    @Override
    protected JobTitle newObject() {
        return new JobTitle();
    }

    @Override
    protected void prepareModel(JobTitle m, ModelMap modelMap) {

    }

    @Override
    protected String singularName() {
        return "Job Title";
    }

    @Override
    protected String pluralName() {
        return "Job Titles";
    }

    @Override
    protected String singularOrPluralName() {
        return "Job Title(s)";
    }

    @Override
    protected String menuGroupName() {
        return MenuConstants.USERS_ROLES_AND_ORG_UNITS_GROUP;
    }

    @Override
    protected String menuItemName() {
        return MenuConstants.JOB_TITLES_PAGE;
    }
}
