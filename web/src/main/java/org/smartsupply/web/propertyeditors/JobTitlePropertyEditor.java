package org.smartsupply.web.propertyeditors;

import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.admin.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("jobTitlePropertyEditor")
public class JobTitlePropertyEditor extends GenericBasePropertyEditor<JobTitle> {
	@Autowired
	private BaseService<JobTitle> jobTitleService;

	@Override
	protected JobTitle getObject(String id) {
		return jobTitleService.getById(id);
	}
}
