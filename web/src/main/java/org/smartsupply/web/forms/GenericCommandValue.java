package org.smartsupply.web.forms;

import java.util.List;

import org.smartsupply.model.enums.StudentStatus;

public class GenericCommandValue {

	private String value;

	private List<StudentStatus> studentStatuses;

	public GenericCommandValue() {
		super();
	}

    public GenericCommandValue(String stringValue) {
		super();
		this.value = stringValue;
	}

	public GenericCommandValue(List<StudentStatus> studentStatuses) {
		super();
		this.studentStatuses = studentStatuses;
	}

	public GenericCommandValue(Boolean booleanValue) {
		super();
		this.value = booleanValue.toString();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String stringValue) {
		this.value = stringValue;
	}

	public List<StudentStatus> getStudentStatuses() {
		return studentStatuses;
	}

	public void setStudentStatuses(List<StudentStatus> studentStatuses) {
		this.studentStatuses = studentStatuses;
	}

	public Boolean getBooleanValue() {
		return Boolean.valueOf(this.getValue());
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.value = booleanValue.toString();
	}

    public <E extends Enum<E>> E getEnumValue(Class<E> clazz) {

        if (clazz == null)
            return null;

        try {
            return Enum.valueOf(clazz, this.getValue());
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getIntValue() {
        try {
            return Integer.parseInt(this.getValue());
        } catch (Exception e) {
            return null;
        }
    }
}
