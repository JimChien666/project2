package com.iii.eeit124.member.dao;

import com.iii.eeit124.entity.MemberFiles;
import com.iii.eeit124.entity.Members;

public interface LoginDao {
	public Members checkIDPassword(String userId, String password);	
	public MemberFiles getFileById(int id);
}