package org.smartsupply.web.propertyeditors;

import org.smartsupply.api.service.baseclasses.BaseQuickService;
import org.smartsupply.model.order.OrderItem;
import org.smartsupply.model.search.OrderItemSearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("orderItemPropertyEditor")
public class OrderItemPropertyEditor extends GenericBasePropertyEditor<OrderItem> {

    @Autowired
    public BaseQuickService<OrderItem, OrderItemSearchParams> orderitemService;

    @Override
    protected OrderItem getObject(String id) {
        return orderitemService.getById(id);
    }
}
