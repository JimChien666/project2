package com.iii.eeit124.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.article.dao.MemberOptionDAO;
import com.iii.eeit124.entity.MemberOption;

@Transactional
@Service
public class MemberOptionServiceImpl implements MemberOptionService{

	@Autowired(required = false)
	MemberOptionDAO memberOptionDAO;

	@Override
	public void save(MemberOption entity) {
		memberOptionDAO.save(entity);
	}
	
	
}
