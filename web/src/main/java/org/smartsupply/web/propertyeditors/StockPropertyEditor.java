package org.smartsupply.web.propertyeditors;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.product.Stock;
import org.smartsupply.model.search.StockSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("stockPropertyEditor")
public class StockPropertyEditor extends GenericBasePropertyEditor<Stock> {

    @Autowired
    public BaseQuickService<Stock, StockSearchParams> stockService;

    @Override
    protected Stock getObject(String id) {
        return stockService.getById(id);
    }
}
