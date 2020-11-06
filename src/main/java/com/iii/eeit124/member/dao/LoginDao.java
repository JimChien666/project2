package com.iii.eeit124.member.dao;

import com.iii.eeit124.entity.Files;
import com.iii.eeit124.entity.Members;

public interface LoginDao {
	public Members checkIDPassword(String userId, String password);	
	public Files getFileById(int id);
}