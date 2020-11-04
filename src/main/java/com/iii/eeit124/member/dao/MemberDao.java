package com.iii.eeit124.member.dao;

import com.iii.eeit124.entity.Members;

public interface MemberDao {

	public int saveMember(Members mb) ;
	
	public Members queryMember(String id);
	
	public Members checkIDPassword(String userId, String password);	

	boolean accountExists(String account);
}