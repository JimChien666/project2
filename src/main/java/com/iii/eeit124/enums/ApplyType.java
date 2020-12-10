package com.iii.eeit124.enums;

import java.util.Optional;
import java.util.stream.Stream;

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

	public static ApplyType findByCode(String code) {
		Optional<ApplyType> optional = Stream.of(ApplyType.values()).filter(o -> o.getCode().equals(code)).findFirst();

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return ApplyType.PARTICIPANT;
		}
	}

	public static ApplyType findByDesc(String description) {
		Optional<ApplyType> optional = Stream.of(ApplyType.values()).filter(o -> o.getDescription().equals(description))
				.findFirst();

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return ApplyType.PARTICIPANT;
		}
	}

}
