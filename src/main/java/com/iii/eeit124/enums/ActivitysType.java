package com.iii.eeit124.enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum ActivitysType {

	NONE("NONE", "NONE"),
	NORMAL("NORMAL", "一般活動");

	private String code;

	private String description;

	ActivitysType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ActivitysType findByCode(String code) {
		Optional<ActivitysType> optional = Stream.of(ActivitysType.values()).filter(o -> o.getCode().equals(code))
				.findFirst();
		
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return ActivitysType.NONE;
		}
	}
}
