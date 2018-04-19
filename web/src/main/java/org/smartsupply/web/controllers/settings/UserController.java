package org.smartsupply.web.controllers.settings;

import org.smartsupply.api.security.PermissionConstants;
import org.smartsupply.api.security.util.RmsSecurityUtil;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.admin.*;
import org.smartsupply.model.enums.Gender;
import org.smartsupply.model.enums.UserStatus;
import org.smartsupply.model.search.UserSearchParams;
import org.smartsupply.web.MenuConstants;
import org.smartsupply.web.ViewNameConstants;
import org.smartsupply.web.WebConstants;
import org.smartsupply.web.WebUtils;
import org.smartsupply.web.controllers.BaseQuickController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

import static org.smartsupply.api.security.PermissionConstants.DELETE_USER;
import static org.smartsupply.api.security.PermissionConstants.PERM_VIEW_SETTINGS;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(MenuConstants.STAFF_USERS_PAGE)
public class UserController extends BaseQuickController<BaseQuickService<User, UserSearchParams>,
        User, UserSearchParams> {

    @Autowired
    private BaseQuickService<User, UserSearchParams> userService;//UserService userService

    @Autowired
    private BaseService<Role> roleService;

    @Autowired
    private BaseService<JobTitle> jobTitleService;

    @Autowired
    private BaseService<Branch> orgUnitService;

    @RequestMapping(value = "user-types", method = RequestMethod.GET)
    public ModelAndView types(ModelMap modelMap) {
        modelMap.put(WebConstants.CONTENT_HEADER, "User Types");
        WebUtils.addMenuActivators(menuGroupName(), MenuConstants.USER_TYPES_PAGE, modelMap);
        modelMap.put(WebConstants.LIST_ITEMS, UserType.values());
        return new ModelAndView(ViewNameConstants.USER_TYPE_VIEW, modelMap);
    }

    @Secured({PermissionConstants.ADD_USER, PermissionConstants.EDIT_USER})
    @RequestMapping(method = RequestMethod.POST, value = "save")
    public ModelAndView save(@ModelAttribute(WebConstants.OBJECT_KEY) User user, ModelMap modelMap) {
        user.setFirstName(user.getFullName());
        user.setLastName(null);
        return super.save(user, modelMap);
    }

    public void validatePassword(User user) throws Exception {
        if(user.hasNewPassword()){
            if(user.getClearTextPassword().length()<6){
                throw new Exception("Password must be at least 6 characters long");
            }
        }
    }

    protected void prepareObjectForSaving(User user) throws Exception {
        validatePassword(user);
        RmsSecurityUtil.prepUserCredentials(user);
    }

    protected void prepareSearchCommand(UserSearchParams params, ModelMap modelMap) {
        modelMap.put("roles", roleService.all());
        modelMap.put("genders", Gender.values2());
        modelMap.put("branches", orgUnitService.all());
        modelMap.put("userTypes", UserType.getStaffUserTypes());
        super.prepareSearchCommand(params, modelMap);
    }

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = {"view"}, method = GET)
    public ModelAndView view(ModelMap modelMap) {
        return super.view(modelMap);
    }

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = "search", method = POST)
    public ModelAndView search(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY) UserSearchParams params, ModelMap modelMap) {
        return super.search(params, modelMap);
    }

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = "back", method = POST)
    @Override
    public ModelAndView back(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY + "Back") UserSearchParams params, ModelMap modelMap) {
        return search(params, modelMap);
    }

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = "next", method = POST)
    @Override
    public ModelAndView next(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY + "Next") UserSearchParams params, ModelMap modelMap) {
        return search(params, modelMap);
    }

    @Secured({PermissionConstants.EDIT_USER})
    @RequestMapping(value = "edit/{id}", method = GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap modelMap) {
        return super.edit(id, modelMap);
    }

    @Secured({PermissionConstants.ADD_USER})
    @RequestMapping(value = "add", method = GET)
    public ModelAndView add(ModelMap modelMap) {
        return super.add(modelMap);
    }

    @Secured({DELETE_USER})
    @RequestMapping(method = GET, value = "delete/{ids}")
    public ModelAndView delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        return super.delete(ids, modelMap);
    }

    protected void addMenuActivators(ModelMap modelMap) {
        WebUtils.addMenuActivators(menuGroupName(), menuItemName(), modelMap);
    }

    @Override
    protected UserSearchParams getInitialSearchParams() {
        return new UserSearchParams(Arrays.asList(UserType.MANAGER, UserType.SALES_ATTENDANT, UserType.ACCOUNTANT));
    }

    @Override
    protected String viewName() {
        return "userView";
    }

    @Override
    protected String formName() {
        return "userForm";
    }

    @Override
    protected String listKey() {
        return "staffUsers";
    }

    @Override
    public User newObject() {
        return new User();
    }

    @Override
    protected void prepareModel(User m, ModelMap modelMap) throws Exception {
        modelMap.put("roles", roleService.all());
        modelMap.put("genders", Gender.values2());
        modelMap.put("userTypes", UserType.getStaffUserTypes());
        modelMap.put("jobTitles", jobTitleService.all());
        modelMap.put("branches", orgUnitService.all());
    }

    @Override
    protected String singularName() {
        return "User";
    }

    @Override
    protected String pluralName() {
        return "Staff Users";
    }

    @Override
    protected String singularOrPluralName() {
        return "User(s)";
    }

    @Override
    protected BaseQuickService<User, UserSearchParams> getService() {
        return userService;
    }

    @Override
    protected UserSearchParams newSearchParams() {
        return new UserSearchParams();
    }

    @Override
    protected void validateAdd(User user) throws Exception {

    }

    @Override
    protected String menuGroupName() {
        return MenuConstants.USERS_ROLES_AND_ORG_UNITS_GROUP;
    }

    protected String menuItemName() {
        return MenuConstants.STAFF_USERS_PAGE;
    }
}