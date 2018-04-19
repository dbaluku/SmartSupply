package org.smartsupply.web.propertyeditors;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.product.StockProduct;
import org.smartsupply.model.search.StockProductSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.product.StockProduct;
import org.smartsupply.model.search.ProductSearchParams;
import org.smartsupply.model.search.StockProductSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("stockProductPropertyEditor")
public class StockProductPropertyEditor extends GenericBasePropertyEditor<StockProduct> {
    @Autowired
    BaseQuickService<StockProduct, StockProductSearchParams> stockProductService;

    @Override
    protected StockProduct getObject(String id) {
        return stockProductService.getById(id);
    }
}

