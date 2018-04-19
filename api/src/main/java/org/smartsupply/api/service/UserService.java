package org.smartsupply.api.service;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.search.UserSearchParams;


import java.util.List;

public interface UserService extends BaseQuickService<User,UserSearchParams> {

	List<String> getDeatils(User user) throws Exception;

//	List<Intake> getUserIntakes(User user) throws Exception;
//
//	List<Course> getUserCourses(User user) throws Exception;
//
//	List<Branch> getUserOrgUnits(User user) throws Exception;

}
