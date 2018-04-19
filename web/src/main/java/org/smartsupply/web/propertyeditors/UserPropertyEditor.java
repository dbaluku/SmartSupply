package org.smartsupply.web.propertyeditors;

import org.smartsupply.api.service.UserService;
import org.smartsupply.model.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userPropertyEditor")
public class UserPropertyEditor extends GenericBasePropertyEditor<User> {

	@Autowired
	private UserService userService;

	@Override
	protected User getObject(String id) {
		return userService.getById(id);
	}

}
