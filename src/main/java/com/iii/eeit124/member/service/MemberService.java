package com.iii.eeit124.member.service;

import com.iii.eeit124.entity.Members;

public interface MemberService {
	boolean accountExists(String account);
	int saveMember(Members mb);
	Members queryMember(String id);
	public Members checkIDPassword(String userId, String password) ;
}

