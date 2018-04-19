package org.smartsupply.web.controllers.product;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.product.Stock;
import org.smartsupply.model.search.StockSearchParams;
import org.smartsupply.web.controllers.BaseQuickController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller("stockController")
public class StockController extends BaseQuickController<BaseQuickService<Stock,StockSearchParams>,
        Stock,StockSearchParams> {

    @Autowired
    private BaseQuickService<Stock, StockSearchParams> stockService;


    @Override
    protected StockSearchParams getInitialSearchParams() {
        return new StockSearchParams();
    }

    @Override
    protected String viewName() {
        return "stockView";
    }

    @Override
    protected String formName() {
        return "stockForm";
    }

    @Override
    protected String listKey() {
        return "stocklist";
    }

    @Override
    protected Stock newObject() {
        return new Stock();
    }

    @Override
    protected void prepareModel(Stock m, ModelMap modelMap) throws Exception {

    }

    @Override
    protected String singularName() {
        return "stock";
    }

    @Override
    protected String pluralName() {
        return "stocks";
    }

    @Override
    protected String singularOrPluralName() {
        return "Stock(s)";
    }

    @Override
    protected BaseQuickService<Stock, StockSearchParams> getService() {
        return stockService;
    }

    @Override
    protected StockSearchParams newSearchParams() {
        return new StockSearchParams();
    }

    @Override
    protected void validateAdd(Stock stock) throws Exception {

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
