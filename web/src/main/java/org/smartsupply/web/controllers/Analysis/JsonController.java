package org.smartsupply.web.controllers.Analysis;



import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartsupply.api.security.util.RmsSecurityUtil;
import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.api.service.impl.base.BaseQuickServiceImpl;
import org.smartsupply.api.service.impl.product.CategoryServiceImpl;
import org.smartsupply.model.AnalysisModel;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.customer.Customer;
import org.smartsupply.model.order.Order;
import org.smartsupply.model.order.OrderItem;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.search.CustomerSearchParams;
import org.smartsupply.model.search.OrderSearchParams;
import org.smartsupply.model.search.ProductSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("get/json")
public class JsonController {


    @Autowired
    private BaseQuickService<Product, ProductSearchParams> productService;
    @Autowired
    public BaseQuickService<Order,OrderSearchParams> orderService;
    @Autowired
    private BaseService<Branch> orgUnitService;

    @Autowired
    private AnalysisFormsController analysisForms;

//    @Autowired
//    private BaseQuickService<Customer, CustomerSearchParams> customerService;


    @RequestMapping(value = "productsSold", method = RequestMethod.GET)
    public void allProductsSold(HttpServletResponse response) {
        try {

            List<Product> products = productService.get(new ProductSearchParams());

            ArrayList<Object> objects = new ArrayList<>();

            for (Product product : products) {
                HashMap<String, String> map = new HashMap<>();
                map.put("value", product.getId());
                map.put("text", product.getName());
                objects.add(map);
            }

            writeJsonResponse(objects, response);

        } catch (Exception e) {
            logErrorAndSetResponseStatus(response, e);
        }
    }

    @RequestMapping(value = "getOrderItems", method = RequestMethod.GET)
    public void productsold(HttpServletResponse response) throws Exception {
        ArrayList<Object> list = new ArrayList<>();
        List<Order> orderList = orderService.get(new OrderSearchParams());
        Double salesperItem = 0.0;
        for (Order orders:orderList ) {
           List<OrderItem> orderItems = orders.getProducts();

            for (OrderItem orderItem:orderItems)
            {
                salesperItem = orderItem.getQuantity()*orderItem.getProduct().getUnitprice();

                list.add(new AnalysisModel(orderItem.getProduct().getName(),salesperItem));
            }

            writeJsonResponse(list, response);

        }

    }
    @RequestMapping(value = "compareBranches", method = RequestMethod.GET)
    public void compareBranches(HttpServletResponse response)
    {

    }

    @RequestMapping(value ="branchPerformance", method = RequestMethod.POST)
    public void getBranchPerformance(HttpServletResponse response, HttpServletRequest request, ModelMap modelMap) throws IOException {
        String branchname =  request.getParameter("branches");
        Branch branche = orgUnitService.getById(branchname);
        Double salesPerbranch =0.0;
        ArrayList<Object> list = new ArrayList<>();
        List<Order> order = orderService.all();
        for (Order orders:order)
        {
            if (orders.getBranch().equals(branche))
            {
               List<OrderItem> orderItemsList= orders.getProducts();
                for (OrderItem orderItems:orderItemsList)
                {
                    salesPerbranch = orderItems.getProduct().getUnitprice()*orderItems.getQuantity();

                    list.add(new AnalysisModel(orderItems.getProduct().getName(),salesPerbranch));

                }

            }
            writeJsonResponse(list, response);
        }

    }

    @RequestMapping(value = "viewCharts", method = RequestMethod.GET)
    public ModelAndView showCharts(ModelMap modelMap) {
        return new ModelAndView("ordercharts",modelMap);
    }

    public static Logger logStatic = LoggerFactory.getLogger(JsonController.class);

    public static void logErrorAndSetResponseStatus(HttpServletResponse response, Exception e) {
        logStatic.error("Error", e);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public static void writeJsonResponse(ArrayList<Object> objects, HttpServletResponse response) throws java.io.IOException {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        HttpOutputMessage message = new ServletServerHttpResponse(response);
        converter.write(objects, MediaType.APPLICATION_JSON, message);
    }

    public static void writeJsonObjects(Object objects, HttpServletResponse response) throws java.io.IOException {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        HttpOutputMessage message = new ServletServerHttpResponse(response);
        converter.write(objects, MediaType.APPLICATION_JSON, message);
    }



}
