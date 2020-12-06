package com.iii.eeit124.enums;

public enum ApplyType {

	PLANNER("1", "活動策劃人"), 
	PARTICIPANT("2", "參與者");

	private String code;

	private String description;

	ApplyType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
