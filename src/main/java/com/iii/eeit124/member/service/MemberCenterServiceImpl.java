package com.iii.eeit124.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.dao.MemberCenterDao;

@Service
public class MemberCenterServiceImpl implements MemberCenterService{
	@Autowired
	MemberCenterDao dao;
	public Members getMemberById(int id) {
		return dao.getMemberById(id);
	}
}
