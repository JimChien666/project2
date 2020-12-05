package com.iii.eeit124.member.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.iii.eeit124.entity.Members;

public interface MemberCenterService {
	Members getMemberById(Integer memberId);

	Map<String, BigDecimal> getCostHistory(Integer memberId);

	Map<String, Object> getSellingHistory(Integer id);

	Map<String, List<Object>> getSellingCountByDate(Integer id);
}
