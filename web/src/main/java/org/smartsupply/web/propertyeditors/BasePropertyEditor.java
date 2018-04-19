package org.smartsupply.web.propertyeditors;

import org.smartsupply.model.BaseData;

import java.beans.PropertyEditorSupport;

public class BasePropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
	if (super.getValue() != null && super.getValue() instanceof BaseData) {
	    return ((BaseData) super.getValue()).getId();
	}

	return super.getAsText();
    }

}
