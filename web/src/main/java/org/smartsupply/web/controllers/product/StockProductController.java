package org.smartsupply.web.controllers.product;

import org.smartsupply.api.security.util.RmsSecurityUtil;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.product.Stock;
import org.smartsupply.model.product.StockProduct;
import org.smartsupply.model.search.ProductSearchParams;
import org.smartsupply.model.search.StockProductSearchParams;
import org.smartsupply.model.search.StockSearchParams;
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
@RequestMapping("stockproducts")
public class StockProductController extends BaseQuickController<BaseQuickService<StockProduct, StockProductSearchParams>,
        StockProduct, StockProductSearchParams> {

    @Autowired
    BaseQuickService<StockProduct, StockProductSearchParams> stockProductService;

    @Autowired
    BaseQuickService<Stock,StockSearchParams> stockService;

    @Autowired
    BaseQuickService<Product,ProductSearchParams> productService;


    @RequestMapping(method = RequestMethod.GET, value = "view")
    public ModelAndView view( ModelMap modelMap) {
        try {
            User user = RmsSecurityUtil.getLoggedInUser();
        Stock stock = user.getBranch().getStock();
        StockProductSearchParams params = new StockProductSearchParams(stock);
       List<StockProduct>stockProducts =new ArrayList<>();
       stockProducts =getService().get(params);
//           stockProducts = user.getBranch().getStock().getProducts();
            modelMap.put("myproducts",stockProducts);
            modelMap.put("branch",user.getBranch());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(viewName(),modelMap);
    }

    @Override
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add(ModelMap modelMap) {
        try {
            StockProduct stockProduct = new StockProduct();
            modelMap.put(objectKey(), stockProduct);
            List<Product> productList =productService.get(new ProductSearchParams());
            modelMap.put("productlist",productList);
           // validateAdd(stockProduct);
            //prepareModel(stockProduct, modelMap);
            WebUtils.addContentHeader(modelMap, "Add " + singularName());
            addMenuActivators(modelMap);
            return new ModelAndView(formName(), modelMap);
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
            return view(modelMap);
        }
    }

    @RequestMapping(value = "saveproduct")
    public ModelAndView saveProduct(@ModelAttribute("objectKey")StockProduct stockProduct, HttpServletRequest request, ModelMap modelMap) {
        try {
            StockProduct existing = stockProduct;
            if (stockProduct.hasId()) {
                existing = getService().getById(stockProduct.getId());
                existing.copyFrom(stockProduct);
            } else {
//                existing.setDateCreated(new Date());
            }
           User user= RmsSecurityUtil.getLoggedInUser();
            Stock stock =user.getBranch().getStock();
            String product_id =request.getParameter("product");
            Product product=productService.getById(product_id);
            if(stock!=null){
            StockProduct stockProduct1=stockProductService.getUnique(new StockProductSearchParams(stock,product));
            if(stockProduct1!=null){
                Double qnty=stockProduct1.getQuantity();
                String id = stockProduct1.getId();
                Double new_quantity = existing.getQuantity()+qnty;
                existing.setQuantity(new_quantity);
                existing.setId(id);

            }

            existing.setStock(stock);
            existing.setProduct(product);


                getService().validate(existing);
                getService().save(existing);
                WebUtils.addSystemMessage(modelMap, singularName() + " Saved Successfully");
               // WebUtils.logExceptionAndAddErrorMessage(modelMap, singularName() + " Saved Successfully");
                WebUtils.addLongResponseMessage(modelMap, " Saved Successfully");
            }
            else{
                WebUtils.addSystemMessage(modelMap, "Logged In Has no enough resources to add Items ");
                WebUtils.addLongResponseMessage(modelMap, "Logged In Has no enough resources to add Items ");
            }

               // return add(modelMap);

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
            StockProduct stockProduct = getService().getById(id);
            WebUtils.checkAndThrowIdNotFoundException(singularName(), stockProduct);

            modelMap.put(objectKey(), stockProduct);
            modelMap.put("productlist",productService.get(new ProductSearchParams()));
            prepareModel(stockProduct, modelMap);
            WebUtils.addContentHeader(modelMap, "Edit " + singularName());
            addMenuActivators(modelMap);
            return new ModelAndView(formName(), modelMap);
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
        }
        return view(modelMap);
    }



    @Override
    protected StockProductSearchParams getInitialSearchParams() {
        return new StockProductSearchParams();
    }

    @Override
    protected String viewName() {
        return "viewStockProduct";
    }

    @Override
    protected String formName() {
        return "stockproductform";
    }

    @Override
    protected String listKey() {
        return "myproducts";
    }

    @Override
    protected StockProduct newObject() {
        return new StockProduct();
    }

    @Override
    protected void prepareModel(StockProduct m, ModelMap modelMap) throws Exception {

    }

    @Override
    protected String singularName() {
        return "product";
    }

    @Override
    protected String pluralName() {
        return "Products";
    }

    @Override
    protected String singularOrPluralName() {
        return null;
    }

    @Override
    protected BaseQuickService<StockProduct, StockProductSearchParams> getService() {
        return stockProductService;
    }

    @Override
    protected StockProductSearchParams newSearchParams() {
        return new StockProductSearchParams();
    }

    @Override
    protected void validateAdd(StockProduct stockProduct) throws Exception {

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
