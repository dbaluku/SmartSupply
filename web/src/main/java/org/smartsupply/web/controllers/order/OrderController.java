package org.smartsupply.web.controllers.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartsupply.api.security.PermissionConstants;
import org.smartsupply.api.security.util.RmsSecurityUtil;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.customer.Customer;
import org.smartsupply.model.exception.RmsSessionExpiredException;
import org.smartsupply.model.order.Order;
import org.smartsupply.model.order.OrderForm;
import org.smartsupply.model.order.OrderItem;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.product.Stock;
import org.smartsupply.model.product.StockProduct;
import org.smartsupply.model.search.*;
import org.smartsupply.web.WebConstants;
import org.smartsupply.web.WebUtils;
import org.smartsupply.web.controllers.BaseQuickController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.smartsupply.api.security.PermissionConstants.PERM_VIEW_SETTINGS;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "order")
public class OrderController extends BaseQuickController<BaseQuickService<Order,OrderSearchParams>,
        Order,OrderSearchParams> {

    @Autowired
    public BaseQuickService<Order,OrderSearchParams> orderService;

    @Autowired
    public BaseQuickService<Product, ProductSearchParams> productService;

    @Autowired
    public BaseQuickService<OrderItem,OrderItemSearchParams> orderitemService;

    @Autowired
    private BaseQuickService<User, UserSearchParams> userService;//UserService userService

    @Autowired
    private BaseQuickService<Customer, CustomerSearchParams> customerService;

    @Autowired
    private BaseQuickService<StockProduct,StockProductSearchParams> stockProductService;

    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(method = RequestMethod.POST, value = "saveorder")
    public ModelAndView save(@ModelAttribute("newOrder") Order order, HttpServletRequest request, ModelMap modelMap) {
        String quantities = request.getParameter("quantities");
        List<Double> quantities_values = splitQuantities(quantities);
        List<OrderItem>items = new ArrayList<>();
        order.setDate_of_ordering(new Date());
        try {

            User user = RmsSecurityUtil.getLoggedInUser();
            order.setSales_man(user);
            Stock stock = user.getBranch().getStock();
            log.error(stock.getName());
            order.setBranch(user.getBranch());
            items =order.getProducts();
            if(items.size()!=quantities_values.size()){
                WebUtils.addErrorMessage(modelMap, "Mismatching values for products");
            }
            else {
                getService().save(order);


                //Product product =productService.getById("402888e463014eb90163014f5bd90000");
                if (items != null && items.size() > 0) {
                    for (int x = 0; x < items.size(); x++) {
                        OrderItem t = items.get(x);
                        if (t != null) {
                            Product product = productService.getById(t.getProduct().getId());
                            Double qnty = t.getQuantity();
                            StockProduct stockProduct = null;
                            stockProduct =stockProductService.getUnique(
                                    new StockProductSearchParams(stock,product));
                            Double new_quantity =update_product_quantity(stockProduct.getQuantity(),t.getQuantity());
                            stockProduct.setQuantity(new_quantity);
                            stockProduct.setStock(stock);
                            stockProductService.save(stockProduct);

                            log.error(String.valueOf(stockProduct.getQuantity()));
                            log.error(stockProduct.getStock().getId());
                            OrderItem orderItem = new OrderItem(product, order, qnty);
                            orderitemService.save(orderItem);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view(modelMap);
    }

    @Override
    //@Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = {"view"}, method = GET)
    public ModelAndView view(ModelMap modelMap) {
        List<Order> orderList =new ArrayList<>();
        try {
            orderList= orderService.get(new OrderSearchParams());
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelMap.put(listKey(),orderList);
        return new ModelAndView(viewName(),modelMap);
    }

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = "search", method = POST)
    public ModelAndView search(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY) OrderSearchParams params, ModelMap modelMap) {
        return super.search(params, modelMap);
    }

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = "back", method = POST)
    @Override
    public ModelAndView back(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY + "Back") OrderSearchParams params, ModelMap modelMap) {
        return search(params, modelMap);
    }

    @Secured({PERM_VIEW_SETTINGS})
    @RequestMapping(value = "next", method = POST)
    @Override
    public ModelAndView next(@ModelAttribute(WebConstants.SEARCH_PARAMS_KEY + "Next") OrderSearchParams params, ModelMap modelMap) {
        return search(params, modelMap);
    }

   // @Secured({PermissionConstants.EDIT_USER})
    @RequestMapping(value = "edit/{id}", method = GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap modelMap) {
        return super.edit(id, modelMap);
    }

   // @Secured({PermissionConstants.ADD_USER})
    @RequestMapping(value = "add", method = GET)
    public ModelAndView add(ModelMap modelMap) {

        try {
            User user =RmsSecurityUtil.getLoggedInUser();
            List<StockProduct> stockProducts= new ArrayList<>();
            stockProducts =stockProductService.get(new StockProductSearchParams(user.getBranch().getStock(),1.0));
            Order order = new Order();
            modelMap.put("newOrder",order);
            WebUtils.addContentHeader(modelMap, "Add " + singularName());
            modelMap.put("products",stockProducts);
            List<Customer> customers=new ArrayList<>();
            customers= customerService.all();
            modelMap.put("customers",customers);
            List<OrderForm> orderFormList =new ArrayList<>();
            //modelMap.put("orderList",orderFormList);


            return new ModelAndView(formName(), modelMap);
        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
            return view(modelMap);
        }
    }


    @RequestMapping(method = GET, value = "delete/{ids}")
    public ModelAndView delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        return super.delete(ids, modelMap);
    }

    @Override
    protected OrderSearchParams getInitialSearchParams() { return null; }

    @Override
    protected String viewName() { return "vieworders"; }

    @Override
    protected String formName() { return "orderForm"; }

    @Override
    protected String listKey() { return "orders"; }

    @Override
    protected Order newObject() { return new Order(); }

    @Override
    protected void prepareModel(Order m, ModelMap modelMap) throws Exception {

    }

    @Override
    protected String singularName() { return "Order"; }

    @Override
    protected String pluralName() { return "Orders"; }

    @Override
    protected String singularOrPluralName() { return "Order(s)"; }

    @Override
    protected BaseQuickService<Order, OrderSearchParams> getService() {
        return orderService;
    }

    @Override
    protected OrderSearchParams newSearchParams() {
        return null;
    }

    @Override
    protected void validateAdd(Order order) throws Exception {

    }

    @Override
    protected String menuGroupName() {
        return null;
    }

    @Override
    protected String menuItemName() {
        return null;
    }

    public List<Double> splitQuantities(String qnty_str){
        List<Double> int_values = new ArrayList<>();

        //        splitting string
        List<String> stringList = Arrays.asList(qnty_str.split(","));
        for (String str:stringList) {
            double value =0;
            value = Double.parseDouble(str);
            int_values.add(value);
        }
        return int_values;
    }

    public Double update_product_quantity(double stock_quantity, double sold_quantity){
        return stock_quantity-sold_quantity;
    }
}
