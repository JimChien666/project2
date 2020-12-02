package com.iii.eeit124.enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum LimitType {

	NONE("NONE", "NONE");
	
	private String code;

	private String description;

	LimitType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static LimitType findByCode(String code) {
		Optional<LimitType> optional = Stream.of(LimitType.values()).filter(o -> o.getCode().equals(code))
				.findFirst();
		
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return LimitType.NONE;
		}
	}

}
