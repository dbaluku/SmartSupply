package org.smartsupply.api.service.impl.admin;

import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.api.service.impl.base.BaseServiceImpl;
import org.smartsupply.model.admin.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jarcommons.JarValidate.disallowBlank;

@Service("jobTitleService")
@Transactional
public class JobTitleServiceImpl extends BaseServiceImpl<JobTitle> implements BaseService<JobTitle> {

    @Autowired
    public void setBaseDao(BaseDAO<JobTitle> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(JobTitle.class);
    }

    @Override
    public void validate(JobTitle t) throws Exception {
        disallowBlank(t.getName(), "Name");
        disallowBlank(t.getDescription(), "Description");
    }
}
