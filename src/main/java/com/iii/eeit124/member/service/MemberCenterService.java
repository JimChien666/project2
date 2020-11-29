package com.iii.eeit124.member.service;

import java.math.BigDecimal;
import java.util.Map;

import com.iii.eeit124.entity.Members;

public interface MemberCenterService {
	Members getMemberById(Integer memberId);

	Map<String, BigDecimal> getDataPerMonth(Integer memberId);
}
