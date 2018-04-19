package org.smartsupply.web.propertyeditors;

import org.apache.commons.lang.StringUtils;
import org.smartsupply.model.BaseData;

public abstract class GenericBasePropertyEditor<T extends BaseData> extends BasePropertyEditor  {

    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isNotBlank(text) && StringUtils.isNotEmpty(text)) {
            if (StringUtils.equalsIgnoreCase("none", text)) {
                super.setValue(null);
            } else {
                super.setValue(getObject(text));
            }
        } else {
            super.setValue(null);
        }
    }

    protected abstract T getObject(String id);
}
