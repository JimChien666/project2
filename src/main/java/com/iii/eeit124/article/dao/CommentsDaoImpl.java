package com.iii.eeit124.article.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iii.eeit124.entity.Comments;

@Repository
public class CommentsDaoImpl implements CommentsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Comments comments) {
		sessionFactory.getCurrentSession().save(comments);
	}

	@Override
	public List<Comments> select(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Comments> query = session.createQuery("From Comments where forums.id = ?1 order by id desc",
				Comments.class);
		query.setParameter(1, id);
		return query.list();
	}

}
