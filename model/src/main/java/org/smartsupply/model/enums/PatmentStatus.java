package org.smartsupply.model.enums;

public enum PatmentStatus {
	NOTHING_PAID("Nothing Paid"), LESS_THAN_HALF("Less Than Half"), HALF("Half"), FULLY_PAID(
			"Fully Paid");

	/**
	 * constructor with initial specified value
	 * 
	 * @param name
	 */
	PatmentStatus(String name) {
		this.name = name;
	}

	private String name;

	/**
	 * gets the title of the enumerated value
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

};
