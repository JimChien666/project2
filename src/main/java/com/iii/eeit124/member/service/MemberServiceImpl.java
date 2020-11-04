package com.iii.eeit124.member.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.dao.MemberDao;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao dao ;
	

	@Override
	@Transactional
	public int saveMember(Members mb) {
		return dao.saveMember(mb);
	}

	@Override
	@Transactional
	public boolean accountExists(String account) {
		return dao.accountExists(account);
	}

	@Override
	@Transactional
	public Members queryMember(String id) {
		return dao.queryMember(id);
	}

	@Override
	@Transactional
	public Members checkAccountPassword(String account, String password) {
		Members mb = dao.checkIDPassword(account, password);
		return mb;
	}
}
