package org.smartsupply.web.controllers.order;

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

import java.util.ArrayList;
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


    @RequestMapping(method = RequestMethod.POST, value = "saveorder")
//    public  ModelAndView saveorderitems (@ModelAttribute("orderList") List<OrderForm> orderFormList,
//                                         ModelMap modelMap){
//        return view(modelMap);
//    }
    public ModelAndView save(@ModelAttribute("newOrder") Order order, ModelMap modelMap) {

        List<OrderItem>items = new ArrayList<>();
        order.setDate_of_ordering(new Date());
        try {
            User user = RmsSecurityUtil.getLoggedInUser();
            order.setSales_man(user);
            order.setBranch(user.getBranch());
            getService().save(order);
            items =order.getProducts();

            //Product product =productService.getById("402888e463014eb90163014f5bd90000");
            if(items!=null &&items.size()>0) {
                for(int x=0;x<items.size();x++) {
                    OrderItem t = items.get(x);
                    if(t!=null){
                    Product product = productService.getById(t.getProduct().getId());
                    Double qnty = t.getQuantity();
                    OrderItem orderItem = new OrderItem(product, order, qnty);
                    orderitemService.save(orderItem);}
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
            stockProducts =stockProductService.get(new StockProductSearchParams(user.getBranch().getStock()));
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
}
