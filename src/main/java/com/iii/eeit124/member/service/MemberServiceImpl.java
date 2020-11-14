package com.iii.eeit124.member.service;

import org.hibernate.Session;

import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.dao.MemberDao;
import com.iii.eeit124.member.dao.MemberDaoImpl;


public class MemberServiceImpl implements MemberService {

	MemberDao  dao ;
	public MemberServiceImpl(Session session) {
		this.dao = new MemberDaoImpl(session);
	}

	@Override
	public int saveMember(Members mb) {
		return dao.saveMember(mb);
	}

	@Override
	public boolean accountExists(String account) {
		return dao.accountExists(account);
	}

	@Override
	public Members queryMember(String id) {
		return dao.queryMember(id);
	}

	@Override

	public Members checkIDPassword(String userId, String password) {
		Members mb = dao.checkIDPassword(userId, password);
		return mb;
	}
}