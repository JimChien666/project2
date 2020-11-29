package com.iii.eeit124.member.dao;

import java.util.Map;

import com.iii.eeit124.entity.Members;

public interface MemberCenterDao {
	public Members getMemberById(Integer memberId);

	public Map<String, Double> getDataPerMonth(Integer memberId);
}
