package org.smartsupply.web.controllers;

import org.smartsupply.api.security.PermissionConstants;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.BaseData;
import org.smartsupply.web.WebConstants;
import org.smartsupply.web.WebUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.smartsupply.api.security.PermissionConstants.PERM_VIEW_SETTINGS;
import static org.smartsupply.web.WebUtils.addSystemMessage;
import static org.smartsupply.web.WebUtils.logExceptionAndAddErrorMessage;

public abstract class BaseController<T extends BaseService<ModeObject>, ModeObject extends BaseData> {

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(method = RequestMethod.GET, value = "view")
    public ModelAndView view(ModelMap modelMap) {
        return view(1, modelMap);
    }

    @Secured({PermissionConstants.PERM_VIEW_SETTINGS})
    @RequestMapping(method = RequestMethod.GET, value = "view/{pageNo}")
    public ModelAndView view(@PathVariable("pageNo") Integer pageNo, ModelMap modelMap) {
        List<ModeObject> list = getService().all(WebUtils.initializeSearchPageNumber(pageNo));
        modelMap.put(listKey(), list);
        modelMap.put(WebConstants.CONTENT_HEADER, pluralName());
        try {
            Integer totalNumberOfItems = getService().countAll();
            WebUtils.prepareNavigation(totalNumberOfItems, pageNo, modelMap);
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
        }

        modelMap.put("singularName", singularName());
        modelMap.put("pluralName", pluralName());
        addMenuActivators(modelMap);

        return new ModelAndView(viewName(), modelMap);
    }

    @Secured({PermissionConstants.PERM_MANAGE_SETTINGS})
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(ModelMap modelMap) {
        try {
            modelMap.put(objectKey(), newObject());
            prepareModel(newObject(), modelMap);
            addContentHeader(modelMap);
            addMenuActivators(modelMap);
            return new ModelAndView(formName(), modelMap);
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
            return view(1, modelMap);
        }
    }

    @Secured({PermissionConstants.PERM_MANAGE_SETTINGS})
    @RequestMapping(method = RequestMethod.POST, value = "save")
    public ModelAndView save(@ModelAttribute(WebConstants.OBJECT_KEY) ModeObject modelObject, ModelMap modelMap) {
        try {
            ModeObject existing = modelObject;
            if (modelObject.hasId()) {
                existing = getService().getById(modelObject.getId());
                existing.copyFrom(modelObject);
            } else {
//                existing.setDateCreated(new Date());
            }

            getService().validate(existing);
            getService().save(existing);
            addSystemMessage(modelMap, singularName() + " Saved Successfully");

            if (modelObject.addAnother())
                return add(modelMap);
        } catch (Exception e) {
            logExceptionAndAddErrorMessage(modelMap, e);
            WebUtils.addContentHeader(modelMap, "Retry Add/Edit " + singularName());
            try {
                prepareModel(modelObject, modelMap);
                addMenuActivators(modelMap);
                return new ModelAndView(formName(), modelMap);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return view(1, modelMap);
    }

    @Secured({PermissionConstants.PERM_MANAGE_SETTINGS})
    @RequestMapping(method = RequestMethod.GET, value = "edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, ModelMap modelMap) {
        try {
            ModeObject modeObject = getService().getById(id);
            WebUtils.checkAndThrowIdNotFoundException(singularName(), modeObject);

            modelMap.put(objectKey(), modeObject);
            prepareModel(modeObject, modelMap);
            editContentHeader(modelMap);
            addMenuActivators(modelMap);
            return new ModelAndView(formName(), modelMap);
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
        }
        return view(1, modelMap);
    }

    @Secured({PermissionConstants.PERM_MANAGE_SETTINGS})
    @RequestMapping(method = RequestMethod.GET, value = "delete/{ids}")
    public ModelAndView delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        try {
            getService().deleteByIds(ids.split(","));
            WebUtils.addSystemMessage(modelMap, singularOrPluralName() + " Successfully Deleted");
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
        }
        return view(1, modelMap);
    }

    protected void addMenuActivators(ModelMap modelMap) {
        WebUtils.addMenuActivators(menuGroupName(), menuItemName(), modelMap);
    }

    protected void editContentHeader(ModelMap modelMap) {
        WebUtils.addContentHeader(modelMap, "Edit " + singularName());
    }

    protected void addContentHeader(ModelMap modelMap) {
        WebUtils.addContentHeader(modelMap, "Add " + singularName());
    }

    protected abstract String viewName();

    protected abstract String formName();

    protected abstract String objectKey();

    protected abstract String listKey();

    protected abstract T getService();

    protected abstract ModeObject newObject();

    protected abstract void prepareModel(ModeObject m, ModelMap modelMap) throws Exception;

    protected abstract String singularName();

    protected abstract String pluralName();

    protected abstract String singularOrPluralName();

    protected abstract String menuGroupName();

    protected abstract String menuItemName();
}
