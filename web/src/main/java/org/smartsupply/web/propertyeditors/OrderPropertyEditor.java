package org.smartsupply.web.propertyeditors;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.order.Order;
import org.smartsupply.model.search.OrderSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("orderPropertyEditor")
public class OrderPropertyEditor extends GenericBasePropertyEditor<Order> {

    @Autowired
    public BaseQuickService<Order, OrderSearchParams> orderService;

    @Override
    protected Order getObject(String id) {
        return orderService.getById(id);
    }
}
