package com.iii.eeit124.member.service;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.MemberFiles;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.dao.RegisterDao;


@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	RegisterDao dao ;
	

	@Override
	@Transactional
	public int saveRegister(Members mb, Set<MemberFiles> files) {
		int num = dao.saveMember(mb);
		for(MemberFiles file:files) {
			file.setMember(mb);
			dao.saveFile(file);
		}
		return num;
	}
	
	@Override
	@Transactional
	public int saveMember(Members mb) {
		int num = dao.saveMember(mb);
		return num;
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
}
