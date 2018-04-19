package org.smartsupply.web.propertyeditors;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.search.OrgUnitSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("orgUnitPropertyEditor")
public class OrgUnitPropertyEditor extends GenericBasePropertyEditor<Branch> {

    @Autowired
    private BaseQuickService<Branch,OrgUnitSearchParams> orgUnitService;

    @Override
    protected Branch getObject(String id) {
        return orgUnitService.getById(id);
    }
}