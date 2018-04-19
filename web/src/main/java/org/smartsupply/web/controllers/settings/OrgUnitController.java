package org.smartsupply.web.controllers.settings;

import org.smartsupply.api.service.UserService;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseSearchService;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.admin.JobTitle;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.enums.OrgUnitType;
import org.smartsupply.model.product.Stock;
import org.smartsupply.model.search.OrgUnitSearchParams;
import org.smartsupply.model.search.StockSearchParams;
import org.smartsupply.model.search.UserSearchParams;
import org.smartsupply.web.MenuConstants;
import org.smartsupply.web.ViewNameConstants;
import org.smartsupply.web.WebConstants;
import org.smartsupply.web.WebUtils;
import org.smartsupply.web.controllers.BaseQuickController;
import org.smartsupply.web.controllers.BaseSearchController;
import org.smartsupply.web.controllers.product.StockController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.smartsupply.model.admin.UserType.MANAGER;
import static org.smartsupply.model.admin.UserType.SALES_ATTENDANT;

@Controller
@RequestMapping(MenuConstants.ORG_UNITS_PAGE)
public class OrgUnitController extends BaseQuickController<BaseQuickService<Branch, OrgUnitSearchParams>,
        Branch, OrgUnitSearchParams> {

    @Autowired
    public BaseQuickService<Stock,StockSearchParams> stockService;

    @Autowired
    private BaseService<Branch> orgUnitService;

    @Autowired
    private BaseService<JobTitle> jobTitleService;

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "view")
    public ModelAndView view(ModelMap modelMap) {
        try {
            List<Branch> branches =orgUnitService.all();
            modelMap.put(listKey(),branches);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(viewName(),modelMap);
    }

    @RequestMapping(value = "types", method = RequestMethod.GET)
    public ModelAndView types(ModelMap modelMap) {
        modelMap.put(WebConstants.CONTENT_HEADER, "Organisation Unit Types");
        WebUtils.addMenuActivators(menuGroupName(), MenuConstants.ORG_UNIT_TYPES_PAGE, modelMap);
        modelMap.put("orgUnitTypes", OrgUnitType.values());
        return new ModelAndView(ViewNameConstants.ORG_UNIT_TYPE_VIEW, modelMap);
    }

    @Override
    protected void prepareModel(Branch orgUnit, ModelMap modelMap) throws Exception {
        modelMap.put("staffUsers", userService.searchWithParams(new UserSearchParams(Arrays.asList(MANAGER, SALES_ATTENDANT))));
        modelMap.put("jobTitles", jobTitleService.all());
        List<Branch> orgUnits = orgUnitService.all();
        if (orgUnit != null) {
            orgUnits.remove(orgUnit);
        }
        modelMap.put("orgUnits", orgUnits);
        modelMap.put("orgUnitTypes", OrgUnitType.values());
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "save")
    public ModelAndView save(@ModelAttribute(WebConstants.OBJECT_KEY) Branch branch, ModelMap modelMap) {
        try {
            Branch existing = branch;
            if (branch.hasId()) {
                existing = orgUnitService.getById(branch.getId());
                existing.copyFrom(branch);
            } else {
//                existing.setDateCreated(new Date());
            }

            prepareObjectForSaving(existing);
            orgUnitService.validate(existing);
           Stock stock1 =new Stock(existing.getAbbreviation()+"_stock",true);
            stockService.save(stock1);
            existing.setStock(stock1);
            orgUnitService.save(existing);
            WebUtils.addSystemMessage(modelMap, singularName() + " Saved Successfully");

            if (branch.addAnother())
                return add(modelMap);

        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
            WebUtils.addContentHeader(modelMap, "Retry Add/Edit " + singularName());
            try {
                prepareModel(branch, modelMap);
                addMenuActivators(modelMap);
                return new ModelAndView(formName(), modelMap);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return view(modelMap);
    }

    @Override
    protected String viewName() {
        return ViewNameConstants.ORG_UNIT_VIEW;
    }

    @Override
    protected String formName() {
        return ViewNameConstants.ORG_UNIT_FORM;
    }

    @Override
    public String objectKey() {
        return WebConstants.OBJECT_KEY;
    }

    @Override
    protected OrgUnitSearchParams getInitialSearchParams() {
        return new OrgUnitSearchParams();
    }

    @Override
    protected String listKey() {
        return "orgUnits";
    }

    @Override
    protected Branch newObject() {
        return new Branch();
    }

    @Override
    protected String singularName() {
        return "Organisation Unit / Department";
    }

    @Override
    protected String pluralName() {
        return "Organisation Units / Departments";
    }

    @Override
    protected String singularOrPluralName() {
        return "Organisation Unit(s) / Department(s)";
    }

    @Override
    protected BaseQuickService<Branch, OrgUnitSearchParams> getService() {
        return null;
    }

    @Override
    protected OrgUnitSearchParams newSearchParams() {
        return new OrgUnitSearchParams();
    }

    @Override
    protected void validateAdd(Branch baseData) throws Exception {

    }

    @Override
    protected String menuGroupName() {
        return MenuConstants.USERS_ROLES_AND_ORG_UNITS_GROUP;
    }

    @Override
    protected String menuItemName() {
        return MenuConstants.ORG_UNITS_PAGE;
    }

}
