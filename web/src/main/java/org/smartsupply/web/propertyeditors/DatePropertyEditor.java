package org.smartsupply.web.propertyeditors;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

// handles web data binding for date types
@Component("datePropertyEditor")
public class DatePropertyEditor extends BasePropertyEditor {

    // The date format used to format a Date into a String
    public final static String[] DEFAULT_OUTPUT_FORMAT = {"dd/MM/yyyy hh:mm:ss", "dd/MM/yyyy"};

    // The date formats used to parse a String into a Date
    public final static String[] DEFAULT_INPUT_FORMATS = {
            "dd/MM/yyyy hh:mm:ss", "dd-MM-yyyy hh:mm:ss", "dd/MM/yy hh:mm:ss",
            "dd-MM-yy hh:mm:ss", "dd/MM/yyyy", "dd-MM-yyyy", "dd/MM/yy", "dd-MM-yy"};

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private SimpleDateFormat dateFormatWithoutTime = new SimpleDateFormat("dd/MM/yyyy");

    public DatePropertyEditor() {

    }


    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            if (StringUtils.isBlank(text) && StringUtils.isEmpty(text)) {
                setValue(null);
            } else if (!StringUtils.isBlank(text) && !StringUtils.isEmpty(text)) {
                if (text.length() <= 10) {
                    setValue(dateFormatWithoutTime.parse(text));
                } else {
                    setValue(dateFormat.parse(text));
                }

            } else {
                throw new IllegalArgumentException(
                        "The text specified for parsing is null");
            }
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Could not parse text [" + text
                    + "] into any available date input formats", ex);
        }
    }

    @Override
    public String getAsText() {
        return super.getValue() != null ? dateFormat.format(super.getValue()) : "";
    }
}