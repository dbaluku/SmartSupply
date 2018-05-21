package org.smartsupply.web.controllers.product;

import org.smartsupply.api.security.PermissionConstants;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.enums.ProductType;
import org.smartsupply.model.enums.QuantityType;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.search.ProductSearchParams;
import org.smartsupply.web.WebConstants;
import org.smartsupply.web.WebUtils;
import org.smartsupply.web.controllers.BaseController;
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

import java.util.ArrayList;
import java.util.List;

import static org.smartsupply.api.security.PermissionConstants.PERM_VIEW_SETTINGS;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/product")
@Controller("productController")
public class ProductController extends BaseQuickController<BaseQuickService<Product,ProductSearchParams>,
        Product, ProductSearchParams> {

    @Autowired
    public BaseQuickService<Product, ProductSearchParams> productService;


    @RequestMapping(method = RequestMethod.POST, value = "save")
    public ModelAndView save(@ModelAttribute(WebConstants.OBJECT_KEY) Product product, ModelMap modelMap) {
        return super.save(product, modelMap);
    }

    @Override
    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = {"view"}, method = GET)
    public ModelAndView view(ModelMap modelMap) {
        List<Product> productList =new ArrayList<>();
        try {
            productList= getService().get(new ProductSearchParams());
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelMap.put(listKey(),productList);
        return new ModelAndView(viewName(),modelMap);
    }

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = "search", method = POST)
    public ModelAndView search(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY) ProductSearchParams params, ModelMap modelMap) {
        return super.search(params, modelMap);
    }

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = "back", method = POST)
    @Override
    public ModelAndView back(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY + "Back") ProductSearchParams params, ModelMap modelMap) {
        return search(params, modelMap);
    }

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = "next", method = POST)
    @Override
    public ModelAndView next(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY + "Next") ProductSearchParams params, ModelMap modelMap) {
        return search(params, modelMap);
    }

    @Secured({PermissionConstants.EDIT_USER})
    @RequestMapping(value = "edit/{id}", method = GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap modelMap) {
        return super.edit(id, modelMap);
    }

    //@Secured({PermissionConstants.ADD_USER})
    @RequestMapping(value = "add", method = GET)
    public ModelAndView add(ModelMap modelMap) {

            Product product = new Product();
            modelMap.put("product", product);
            WebUtils.addContentHeader(modelMap, "Add " + singularName());
            modelMap.put("productTypes", ProductType.values2());
            modelMap.put("quantityTypes", QuantityType.values2());
            return new ModelAndView(formName(), modelMap);

    }


    @RequestMapping(method = GET, value = "delete/{ids}")
    public ModelAndView delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        return super.delete(ids, modelMap);
    }
    @Override
    protected ProductSearchParams getInitialSearchParams() {
        return new ProductSearchParams();
    }

    @Override
    protected String viewName() {
        return "viewProducts";
    }

    @Override
    protected String formName() {
        return "productForm";
    }

    @Override
    protected String listKey() {
        return "myproducts";
    }

    @Override
    protected Product newObject() {
        return new Product();
    }


    @Override
    protected String singularName() {
        return "product";
    }

    @Override
    protected String pluralName() {
        return "products";
    }

    @Override
    protected String singularOrPluralName() {
        return "product(s)";
    }

    @Override
    protected BaseQuickService<Product, ProductSearchParams> getService() {
        return productService;    }

    @Override
    protected ProductSearchParams newSearchParams() {
        return new ProductSearchParams();
    }

    @Override
    protected void validateAdd(Product product) throws Exception {
        getService().validate(product);

    }

    @Override
    protected void prepareModel(Product m, ModelMap modelMap) throws Exception {

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
