package com.iii.eeit124.member.service;


import com.iii.eeit124.entity.MemberFiles;
import com.iii.eeit124.entity.Members;

public interface LoginService {
	public Members checkAccountPassword(String account, String password) ;
	public MemberFiles getFileById(int id);
}
