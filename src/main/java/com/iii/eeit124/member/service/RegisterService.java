package com.iii.eeit124.member.service;

import java.util.Set;

import com.iii.eeit124.entity.Files;
import com.iii.eeit124.entity.Members;

public interface RegisterService {
	boolean accountExists(String account);
	int saveRegister(Members mb, Set<Files> files);
	Members queryMember(String id);
}
