package org.smartsupply.web.controllers.Analysis;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.model.AnalysisModel;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.order.Order;
import org.smartsupply.model.order.OrderItem;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.product.StockProduct;
import org.smartsupply.model.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("getAnalysisForms")
public class AnalysisFormsController {
    @Autowired
    public BaseQuickService<Order, OrderSearchParams> orderService;

    @Autowired
    private BaseService<Branch> orgUnitService;

    @Autowired
    JsonController jsonController;
    @Autowired
    private BaseQuickService<User, UserSearchParams> userService;

    @Autowired
    public BaseQuickService<StockProduct, StockProductSearchParams> stockProductService;


    @RequestMapping(value = "chooseBranch", method = RequestMethod.GET)
    public ModelAndView displayBranches(ModelMap modelMap) throws Exception {
        List<Branch> branches = orgUnitService.all();
        modelMap.put("branches", branches);
        return new ModelAndView("choosebranches", modelMap);
    }
    @RequestMapping(value = "salesperson", method = RequestMethod.POST)
    public ModelAndView displaySalesPeople(ModelMap modelMap,HttpServletRequest request) throws Exception {
        String branchname = request.getParameter("branchList");
        Branch branch = orgUnitService.getById(branchname);
        List<AnalysisModel> analysisModels = getIndivdualPerfomance(branch);
        modelMap.put("salesperson",analysisModels);
        return new ModelAndView("salesperson", modelMap);
    }


    @RequestMapping(value = "branchPerformance", method = RequestMethod.POST)
    public ModelAndView getBranchPerformance(HttpServletResponse response, HttpServletRequest request, ModelMap modelMap) throws Exception {
        String branchname = request.getParameter("branches");
        Branch branch = orgUnitService.getById(branchname);
         List<AnalysisModel> analysisModelList = getBranchSales(branch);
         modelMap.put("itemsLists",analysisModelList);
        return new ModelAndView("branchSales", modelMap);

        }

        @RequestMapping(value = "staffPerformance", method = RequestMethod.GET)
        public ModelAndView getIndividualdata(ModelMap modelMap) throws Exception {
            List<Branch> branchList = orgUnitService.all();
            modelMap.put("branches", branchList);
            return new ModelAndView("branchForm", modelMap);
        }


    public List<AnalysisModel> getBranchSales(Branch branch) throws Exception {
        List<Order> orders = orderService.get(new OrderSearchParams(branch));
        List<OrderItem> orderItemList = new ArrayList<>();
        List<StockProduct> stock_products = stockProductService.get(new StockProductSearchParams(branch.getStock()));
        List<AnalysisModel> analysisModels = new ArrayList<>();
        if (!orders.isEmpty() && orders.size() > 0 && !stock_products.isEmpty())
            for (StockProduct product : stock_products) {
                double tt = 0.0;
                for (Order order : orders) {
                    orderItemList = order.getProducts();
                    if (!orderItemList.isEmpty() && orderItemList.size() > 0) {

                        for (OrderItem item : orderItemList) {
                            if (product.getProduct().equals(item.getProduct())) {
                                Product product1 = item.getProduct();
                                double quantity = item.getQuantity();
                                tt = tt + (product1.getUnitprice() * quantity);
                            }
                        }

                    }
                }
                AnalysisModel model = new AnalysisModel(product.getProduct().getName(), tt);
                analysisModels.add(model);

            }

        return analysisModels;
}
    public List<AnalysisModel> getIndivdualPerfomance(Branch branch) throws Exception {

        List<User> salesperson = new ArrayList<>();
        List<AnalysisModel> analysisModelsList = new ArrayList<>();
        List<User> users = userService.get(new UserSearchParams());
        if (!users.isEmpty() && users.size() > 0) {
            for (User user : users) {
                if (user.getBranch().equals(branch)) {
                    salesperson.add(user);
                }
            }

            for (User staff : salesperson) {
                double salesperIndividual = 0.0;
                List<Order> orders = orderService.get(new OrderSearchParams(staff));
                for (Order ordersales : orders) {
                    salesperIndividual += ordersales.getTotal_amount();
                }

                AnalysisModel model = new AnalysisModel(staff.getFullName(), salesperIndividual);
                analysisModelsList.add(model);
            }


        }
        return analysisModelsList;
    }
}
