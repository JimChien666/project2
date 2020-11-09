package com.iii.eeit124.member.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.MemberFiles;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.dao.LoginDao;



@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDao dao ;

	@Override
	@Transactional
	public Members checkAccountPassword(String account, String password) {
		Members mb = dao.checkIDPassword(account, password);
		return mb;
	}
	@Override
	@Transactional
	public MemberFiles getFileById(int id) {
		MemberFiles file = dao.getFileById(id);
		return file;
	}
}
