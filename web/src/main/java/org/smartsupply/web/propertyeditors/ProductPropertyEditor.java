package org.smartsupply.web.propertyeditors;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.search.ProductSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("productPropertyEditor")
public class ProductPropertyEditor extends GenericBasePropertyEditor<Product> {

    @Autowired
    public BaseQuickService<Product, ProductSearchParams> productService;

    @Override
    protected Product getObject(String id) {
        return productService.getById(id);
    }
}
