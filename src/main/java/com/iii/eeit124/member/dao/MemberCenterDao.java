package com.iii.eeit124.member.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.iii.eeit124.entity.Members;

public interface MemberCenterDao {
	public Members getMemberById(Integer memberId);

	public Map<String, BigDecimal> getCostHistory(Integer memberId);

	public Map<String, Object> getSellingHistory(Integer memberId);

	public Map<String, List<Object>> getSellingCountByDate(Integer memberId);

	public Map<String, Object> getSellingHistory(Integer memberId, Date start, Date last);

	public Map<String, List<Object>> getSellingCountByDate(Integer memberId, Date start, Date last);
}
