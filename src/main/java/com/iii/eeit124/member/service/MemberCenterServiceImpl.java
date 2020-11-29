package com.iii.eeit124.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.dao.MemberCenterDao;

@Service
public class MemberCenterServiceImpl implements MemberCenterService{
	@Autowired
	MemberCenterDao dao;
	public Members getMemberById(Integer memberId) {
		return dao.getMemberById(memberId);
	}
	@Override
	public Map<String, Double> getDataPerMonth(Integer memberId) {
		// TODO Auto-generated method stub
		return dao.getDataPerMonth(memberId);
	}
	
}
