package com.iii.eeit124.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.article.dao.ForumsDao;
import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.Forums;

@Transactional
@Service
public class ForumsServiceImpl implements ForumsService {
	
	@Autowired(required = false)
	ForumsDao forumsDao;

	@Override
	public List<Forums> select(Integer pageNo,Integer recordsPerPage,Integer id) {
		return forumsDao.select(pageNo,recordsPerPage,id);
	}

	@Override
	public void save(Forums forums) {
		forumsDao.save(forums);
	}

	@Override
	public void saveArticle(Article article) {
		forumsDao.saveArticle(article);
		
	}

	@Override
	public void update(Article article) {
		forumsDao.updateArticle(article);
	}

	@Override
	public Forums selectForum(int id) {
		return forumsDao.selectForum(id);
	}

	@Override
	public Long getRecordCounts(Integer id) {
		return forumsDao.getRecordCounts(id);
	}
	

}
