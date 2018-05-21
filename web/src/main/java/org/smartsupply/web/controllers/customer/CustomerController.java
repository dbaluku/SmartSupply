package org.smartsupply.web.controllers.customer;

import org.smartsupply.api.security.util.RmsSecurityUtil;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.customer.Customer;
import org.smartsupply.model.search.CustomerSearchParams;
import org.smartsupply.web.WebUtils;
import org.smartsupply.web.controllers.BaseQuickController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "customer")
public class CustomerController extends BaseQuickController<BaseQuickService<Customer, CustomerSearchParams>,
        Customer, CustomerSearchParams> {

    @Autowired
    BaseQuickService<Customer, CustomerSearchParams> customerService;



    @RequestMapping(method = RequestMethod.GET, value = "view")
    public ModelAndView view(ModelMap modelMap) {
        try {

            List<Customer> customers =new ArrayList<>();
            customers =getService().get(getInitialSearchParams());
//           stockProducts = user.getBranch().getStock().getProducts();
            modelMap.put("customers",customers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(viewName(),modelMap);
    }

    @Override
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(ModelMap modelMap) {
        try {
            Customer stockProduct = new Customer();
            modelMap.put(objectKey(), stockProduct);
            WebUtils.addContentHeader(modelMap, "Add " + singularName());
            addMenuActivators(modelMap);
            return new ModelAndView(formName(), modelMap);
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
            return view(modelMap);
        }
    }

    @RequestMapping(value = "save/customer")
    public ModelAndView saveProduct(@ModelAttribute("objectKey")Customer customer, HttpServletRequest request, ModelMap modelMap) {
        try {
            Customer existing = customer;
            if (customer.hasId()) {
                existing = getService().getById(customer.getId());
                existing.copyFrom(customer);
            } else {
//                existing.setDateCreated(new Date());


                getService().validate(existing);
                getService().save(existing);
                WebUtils.addSystemMessage(modelMap, singularName() + " Saved Successfully");
                // WebUtils.logExceptionAndAddErrorMessage(modelMap, singularName() + " Saved Successfully");
                WebUtils.addLongResponseMessage(modelMap, " Saved Successfully");
            }

        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
            WebUtils.addContentHeader(modelMap, "Retry Add/Edit " + singularName());

        }
//        //return view(modelMap);
        return add(modelMap);
    }



    @Override
    @RequestMapping(method = RequestMethod.GET, value = "edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, ModelMap modelMap) {
        try {
           Customer customer= getService().getById(id);
            WebUtils.checkAndThrowIdNotFoundException(singularName(), customer);

            modelMap.put(objectKey(), customer);
            WebUtils.addContentHeader(modelMap, "Edit " + singularName());
            addMenuActivators(modelMap);
            return new ModelAndView(formName(), modelMap);
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
        }
        return view(modelMap);
    }



    @Override
    protected CustomerSearchParams getInitialSearchParams() {
        return new CustomerSearchParams();
    }

    @Override
    protected String viewName() {
        return "viewCustomers";
    }

    @Override
    protected String formName() {
        return "addcustomerform";
    }

    @Override
    protected String listKey() {
        return "customers";
    }

    @Override
    protected Customer newObject() {
        return new Customer();
    }

    @Override
    protected void prepareModel(Customer m, ModelMap modelMap) throws Exception {

    }


    @Override
    protected String singularName() {
        return "Customer";
    }

    @Override
    protected String pluralName() {
        return "Customers";
    }

    @Override
    protected String singularOrPluralName() {
        return null;
    }

    @Override
    protected BaseQuickService<Customer,CustomerSearchParams> getService() {
        return customerService;
    }

    @Override
    protected CustomerSearchParams newSearchParams() {
        return new CustomerSearchParams();
    }

    @Override
    protected void validateAdd(Customer customer) throws Exception {

    }

    @Override
    protected String menuGroupName() {
        return null;
    }

    @Override
    protected String menuItemName() {
        return null;
    }
}
