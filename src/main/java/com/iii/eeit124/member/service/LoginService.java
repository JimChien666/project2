package com.iii.eeit124.member.service;


import com.iii.eeit124.entity.Files;
import com.iii.eeit124.entity.Members;

public interface LoginService {
	public Members checkAccountPassword(String account, String password) ;
	public Files getFileById(int id);
}
