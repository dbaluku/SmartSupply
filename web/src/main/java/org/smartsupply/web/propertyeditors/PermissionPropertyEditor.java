package org.smartsupply.web.propertyeditors;

import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.admin.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("permissionPropertyEditor")
public class PermissionPropertyEditor extends GenericBasePropertyEditor<Permission> {

	@Autowired
	private BaseService<Permission> permissionService;

	@Override
	protected Permission getObject(String id) {
		return permissionService.getById(id);
	}
}
