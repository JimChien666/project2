package com.iii.eeit124.article.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.ArticleTypes;
import com.iii.eeit124.entity.Forums;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Article saveFullArticle(Article article) {
		Session session = sessionFactory.getCurrentSession();
		Article result = session.get(Article.class, article.getId());
		if (result==null) {
			java.util.Date now = new java.util.Date();
			Date date = new Date(now.getTime());
			
		}
		return null;
	}

	@Override
	public void save(Article entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void update(Article entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void delete(Article entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public List<ArticleTypes> getAllArticleTypes() {
		Session session = sessionFactory.getCurrentSession();
		Query<ArticleTypes> query = session.createQuery("From ArticleTypes", ArticleTypes.class);
//		Query<ArticleTypes> query = session.createQuery("select id, articletype From ArticleTypes", ArticleTypes.class);
		List<ArticleTypes> list = query.list();
		return list;
	}

	@Override
	public List<Article> getAllArticles(int articletypesId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Article> query = session.createQuery("from Article where articletypes_id = ?1 order by id desc",
				Article.class);
		query.setParameter(1, articletypesId);
		List<Article> list = query.list();
		return list;
	}

	@Override
	public Article select(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Article> query = session.createQuery("from Article where id = ?1",Article.class);
		query.setParameter(1, id);		
		return query.uniqueResult();
	}





}
