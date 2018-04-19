package org.smartsupply.model.enums;

public enum TrueFalse {

	TRUE("True"), FALSE("False");

	TrueFalse(String name) {
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return this.name;
	}

	public Boolean getAsBooleanValue() {
		return Boolean.valueOf(this.getName());
	}

	public static Boolean getAsBooleanValue(TrueFalse value) {
		return value == null? null: value.getAsBooleanValue();
	}

	public static TrueFalse getAsTrueFalse(Boolean value) {
		return (value == null)? null: value? TRUE:FALSE;
	}

	public static TrueFalse valueOf2(String value){
		for(TrueFalse t: TrueFalse.values()){
			if(value.equalsIgnoreCase(t.getName())){
				return t;
			}
		}
		return null;
	}

}
