package com.iii.eeit124.article.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.article.dao.CommentsDao;
import com.iii.eeit124.entity.Comments;

@Transactional
@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	CommentsDao commentsDao;

	@Override
	public void save(Comments comments) {
		commentsDao.save(comments);
	}

	@Override
	public List<Comments> select(int id) {
		return commentsDao.select(id);
	}

}
