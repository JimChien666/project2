package com.iii.eeit124.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.article.dao.ForumsDao;
import com.iii.eeit124.entity.Forums;
@Transactional
@Service
public class ForumsServiceImpl implements ForumsService{
	@Autowired(required = false)
	ForumsDao forumsDao;
	
	@Override
	public List<Forums> select(int id) {
		return forumsDao.select(id);
	}

}
