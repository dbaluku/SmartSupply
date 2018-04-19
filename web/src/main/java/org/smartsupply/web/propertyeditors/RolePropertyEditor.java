package org.smartsupply.web.propertyeditors;

import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.admin.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("rolePropertyEditor")
public class RolePropertyEditor extends GenericBasePropertyEditor<Role> {

	@Autowired
	private BaseService<Role> roleService;

	@Override
	protected Role getObject(String id) {
		return roleService.getById(id);
	}
}
