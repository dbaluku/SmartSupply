package org.smartsupply.web.controllers.settings;

import org.apache.commons.lang.StringUtils;
import org.smartsupply.api.security.PermissionConstants;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.admin.Permission;
import org.smartsupply.model.admin.Role;
import org.smartsupply.web.MenuConstants;
import org.smartsupply.web.ViewNameConstants;
import org.smartsupply.web.WebConstants;
import org.smartsupply.web.WebUtils;
import org.smartsupply.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.smartsupply.web.WebUtils.addSystemMessage;
import static org.smartsupply.web.WebUtils.logExceptionAndAddErrorMessage;

@Controller
@RequestMapping(MenuConstants.ROLES_PAGE)
public class RoleController extends BaseController<BaseService<Role>, Role> {

    @Autowired
    private BaseService<Role> roleService;

    @Autowired
    private BaseService<Permission> permissionService;


    @Secured(PermissionConstants.MANAGE_ROLES)
    @RequestMapping(method = RequestMethod.GET, value = "add")
    public ModelAndView add(ModelMap modelMap) {
        prepareRoleFormModel(modelMap,new Role());
        addContentHeader(modelMap);
        return new ModelAndView(ViewNameConstants.ROLE_FORM, modelMap);
    }


    private void prepareRoleFormModel(ModelMap modelMap,Role role) {

        try {
            modelMap.put("permissions", permissionService.all());

            modelMap.put(WebConstants.OBJECT_KEY, role);
            addMenuActivators(modelMap);
        } catch (Exception e) {
            logExceptionAndAddErrorMessage(modelMap, e);
        }
    }





    @Secured(PermissionConstants.MANAGE_ROLES)
    @RequestMapping(value = "edit/{roleId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("roleId") String roleId, ModelMap modelMap) {
       Role role =roleService.getById(roleId);
        prepareRoleFormModel(modelMap,role);
        addContentHeader(modelMap);
        return new ModelAndView(ViewNameConstants.ROLE_FORM, modelMap);
    }

    @Secured({PermissionConstants.MANAGE_ROLES})
    @RequestMapping(method = RequestMethod.POST, value = "save")
    public ModelAndView save(@ModelAttribute(WebConstants.OBJECT_KEY) Role role, ModelMap modelMap) {
        try {
            Role existing = role;
            if (role.hasId()) {
                existing = getService().getById(role.getId());
                existing.copyFrom(role);
            } else {
//                existing.setDateCreated(new Date());
            }

            getService().validate(existing);
            getService().save(existing);
            addSystemMessage(modelMap, singularName() + " Saved Successfully");

            if (role.addAnother())
                return add(modelMap);
        } catch (Exception e) {
            logExceptionAndAddErrorMessage(modelMap, e);
            WebUtils.addContentHeader(modelMap, "Add/Edit " + singularName());
            prepareRoleFormModel(modelMap, role);
            return new ModelAndView(formName(), modelMap);
        }
        return view(1, modelMap);
    }

    @Secured({PermissionConstants.MANAGE_ROLES})
    @RequestMapping(method = RequestMethod.GET, value = "delete/{ids}")
    public ModelAndView delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        return super.delete(ids, modelMap);
    }

    @Override
    protected String viewName() {
        return ViewNameConstants.ROLE_VIEW;
    }

    @Override
    protected String formName() {
        return ViewNameConstants.ROLE_FORM;
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
    protected BaseService<Role> getService() {
        return roleService;
    }

    @Override
    protected Role newObject() {
        return new Role();
    }

    @Override
    protected void prepareModel(Role m, ModelMap modelMap) {

    }

    @Override
    protected String singularName() {
        return "Role";
    }

    @Override
    protected String pluralName() {
        return "Roles";
    }

    @Override
    protected String singularOrPluralName() {
        return "Role(s)";
    }

    @Override
    protected String menuGroupName() {
        return MenuConstants.USERS_ROLES_AND_ORG_UNITS_GROUP;
    }

    @Override
    protected String menuItemName() {
        return MenuConstants.ROLES_PAGE;
    }

    @Secured({PermissionConstants.PERM_VIEW_SETTINGS})
    @RequestMapping(method = RequestMethod.GET, value = MenuConstants.PERMISSIONS_PAGE)
    public ModelAndView viewPermissions(ModelMap modelMap) {
        List<Permission> list = permissionService.all();
        modelMap.put(listKey(), list);
        modelMap.put(WebConstants.CONTENT_HEADER, "Permissions");
        WebUtils.addMenuActivators(menuGroupName(), "permissions", modelMap);
        return new ModelAndView(ViewNameConstants.PERMISSION_VIEW, modelMap);
    }
}
