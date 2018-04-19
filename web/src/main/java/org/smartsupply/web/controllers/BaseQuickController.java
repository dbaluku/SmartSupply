package org.smartsupply.web.controllers;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.BaseData;
import org.smartsupply.model.ModelUtil;
import org.smartsupply.model.search.BaseSearchParams;
import org.smartsupply.web.WebConstants;
import org.smartsupply.web.WebUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

public abstract class BaseQuickController<T extends BaseQuickService<ModeObject, SearchParams>,
        ModeObject extends BaseData, SearchParams extends BaseSearchParams> {

    @RequestMapping(method = RequestMethod.GET, value = "view")
    public ModelAndView view(ModelMap modelMap) {
        return search(getInitialSearchParams(), modelMap);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)

    public ModelAndView search(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY) SearchParams params, ModelMap modelMap) {
        modelMap.put(WebConstants.CONTENT_HEADER, pluralName());
        modelMap.put("singularName", singularName());
        modelMap.put("pluralName", pluralName());

        params.initializePageNumber();
        prepareSearchModel(params, modelMap);
        addMenuActivators(modelMap);
        return new ModelAndView(viewName(), modelMap);
    }

    public void paginate(SearchParams params, ModelMap modelMap) throws Exception {
        if (params.getPageNo() > 1) {
            SearchParams paramsBack = newSearchParams();
            ModelUtil.copySearchParams(paramsBack, params);
            paramsBack.setPageNo(params.getPageNo() - 1);
            modelMap.put(WebConstants.SEARCH_PARAMS_KEY + "Back", paramsBack);
        } else {
            modelMap.remove(WebConstants.SEARCH_PARAMS_KEY + "Back");
        }

        int count = getService().number(params);
        int numberOfPages = WebUtils.getNumPages(count);

        if (params.getPageNo() < numberOfPages) {
            SearchParams paramsNext = newSearchParams();
            ModelUtil.copySearchParams(paramsNext, params);
            paramsNext.setPageNo(params.getPageNo() + 1);
            modelMap.put(WebConstants.SEARCH_PARAMS_KEY + "Next", paramsNext);
        } else {
            modelMap.remove(WebConstants.SEARCH_PARAMS_KEY + "Next");
        }

        WebUtils.prepareNavigation(count, params.getPageNo(), modelMap);
    }

    @RequestMapping(value = "back", method = POST)
    public ModelAndView back(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY + "Back") SearchParams params, ModelMap modelMap) {
        return search(params, modelMap);
    }

    @RequestMapping(value = "next", method = POST)
    public ModelAndView next(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY + "Next") SearchParams params, ModelMap modelMap) {
        return search(params, modelMap);
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(ModelMap modelMap) {
        try {
            ModeObject newObject = newObject();
            modelMap.put(objectKey(), newObject);
            validateAdd(newObject);
            prepareModel(newObject, modelMap);
            WebUtils.addContentHeader(modelMap, "Add " + singularName());
            addMenuActivators(modelMap);
            return new ModelAndView(formName(), modelMap);
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
            return view(modelMap);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, ModelMap modelMap) {
        try {
            ModeObject modeObject = getService().getById(id);
            WebUtils.checkAndThrowIdNotFoundException(singularName(), modeObject);

            modelMap.put(objectKey(), modeObject);
            prepareModel(modeObject, modelMap);
            WebUtils.addContentHeader(modelMap, "Edit " + singularName());
            addMenuActivators(modelMap);
            return new ModelAndView(formName(), modelMap);
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
        }
        return view(modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "delete/{ids}")
    public ModelAndView delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        try {
            getService().deleteByIds(ids.split(","));
            WebUtils.addSystemMessage(modelMap, singularOrPluralName() + " Successfully Deleted");
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
        }
        return view(modelMap);
    }

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

            prepareObjectForSaving(existing);

            getService().validate(existing);
            getService().save(existing);
            WebUtils.addSystemMessage(modelMap, singularName() + " Saved Successfully");

            if (modelObject.addAnother())
                return add(modelMap);

        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
            WebUtils.addContentHeader(modelMap, "Retry Add/Edit " + singularName());
            try {
                prepareModel(modelObject, modelMap);
                addMenuActivators(modelMap);
                return new ModelAndView(formName(), modelMap);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return view(modelMap);
    }

    protected void prepareSearchModel(SearchParams searchParams, ModelMap modelMap) {
        try {
            modelMap.put(listKey(),getService().get(searchParams, searchParams.getPageNo()));
            paginate(searchParams, modelMap);
        } catch (Exception e) {
            e.printStackTrace();
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
        }
        prepareSearchCommand(searchParams, modelMap);
    }

    protected void prepareSearchCommand(SearchParams searchParams, ModelMap modelMap) {
        modelMap.put(WebConstants.SEARCH_PARAMS_KEY, searchParams);
    }

    protected void addMenuActivators(ModelMap modelMap) {
        WebUtils.addMenuActivators(menuGroupName(), menuItemName(), modelMap);
    }

    public String objectKey() {
        return WebConstants.OBJECT_KEY;
    }

    protected abstract SearchParams getInitialSearchParams();

    protected abstract String viewName();

    protected abstract String formName();

    protected abstract String listKey();

    protected abstract ModeObject newObject();

    protected abstract void prepareModel(ModeObject m, ModelMap modelMap) throws Exception;

    protected void prepareObjectForSaving(ModeObject m) throws Exception {

    }

    protected abstract String singularName();

    protected abstract String pluralName();

    protected abstract String singularOrPluralName();

    protected abstract T getService();

    protected abstract SearchParams newSearchParams();

    protected abstract void validateAdd(ModeObject modeObject) throws Exception;

    protected abstract String menuGroupName();

    protected abstract String menuItemName();
}
