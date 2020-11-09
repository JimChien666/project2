package com.iii.eeit124.article.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.Forums;
@Repository
public class ForumsDaoImpl implements ForumsDao {

	@Autowired(required = false)
	private SessionFactory sessionFactory;
	

	@Override
	public List<Forums> select(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Forums> query = session.createQuery("From Forums where articleid = ?1", Forums.class);
		query.setParameter(1, id);
		return query.list();
	}
	
	
	
	
	

	@Override
	public void save(Forums forums) {
		sessionFactory.getCurrentSession().save(forums);		
	}

	@Override
	public void saveArticle(Article article) {
		sessionFactory.getCurrentSession().save(article);		
		
	}

}
