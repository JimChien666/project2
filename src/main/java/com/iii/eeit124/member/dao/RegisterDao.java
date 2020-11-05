package com.iii.eeit124.member.dao;

import com.iii.eeit124.entity.Files;
import com.iii.eeit124.entity.Members;

public interface RegisterDao {

	public int saveMember(Members mb) ;
	
	public Members queryMember(String id);
	

	boolean accountExists(String account);
	
	public void saveFile(Files file);
}