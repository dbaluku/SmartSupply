package org.smartsupply.web.controllers.settings;

import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.admin.Url;
import org.smartsupply.model.admin.UserType;
import org.smartsupply.web.MenuConstants;
import org.smartsupply.web.ViewNameConstants;
import org.smartsupply.web.WebConstants;
import org.smartsupply.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(MenuConstants.URLS_PAGE)
public class UrlController extends BaseController<BaseService<Url>, Url> {

    @Autowired
    private BaseService<Url> urlService;

    @Override
    protected String viewName() {
        return ViewNameConstants.URL_VIEW;
    }

    @Override
    protected String formName() {
        return ViewNameConstants.URL_FORM;
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
    protected BaseService<Url> getService() {
        return urlService;
    }

    @Override
    protected Url newObject() {
        return new Url();
    }

    @Override
    protected void prepareModel(Url m, ModelMap modelMap) {
        modelMap.put("userTypes", UserType.values());
    }

    @Override
    protected String singularName() {
        return "URL";
    }

    @Override
    protected String pluralName() {
        return "URLs";
    }

    @Override
    protected String singularOrPluralName() {
        return "URL(s)";
    }

    @Override
    protected String menuGroupName() {
        return MenuConstants.USERS_ROLES_AND_ORG_UNITS_GROUP;
    }

    @Override
    protected String menuItemName() {
        return MenuConstants.URLS_PAGE;
    }
}
