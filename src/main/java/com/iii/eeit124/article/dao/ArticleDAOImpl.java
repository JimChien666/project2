package com.iii.eeit124.article.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

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

	@Override
	public List<Article> select(Integer pageNo, Integer recordsPerPage, Integer id) {
		Integer startRecordNo = (pageNo - 1) * recordsPerPage;
		@SuppressWarnings("unchecked")
		TypedQuery<Article> query = sessionFactory.getCurrentSession().createQuery("from Article where ARTICLETYPES_ID=?0 order by id desc");
		query.setParameter(0, id).setFirstResult(startRecordNo).setMaxResults(recordsPerPage);
		query.getResultList();		
		return query.getResultList();
	}

	@Override
	public Long getRecordCounts(Integer id) {
		Long count = 0L; // 必須使用 long 型態
		count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Article where ARTICLETYPES_ID=?0").setParameter(0, id).getSingleResult();
		return count;
	}

	@Override
	public List<Article> personalFollowed(Integer memberid, Integer id) {
		  Query query = sessionFactory.getCurrentSession().createQuery("select articleid from FollowedArticle where memeberid = ?1 and status = 1 order by id desc");
		  query.setParameter(1, memberid);
		  List resultList = query.getResultList();
		  List<Article> resultList2 = new ArrayList();
		  if(resultList.size()!= 0) {
		   Query<Article> query2 = sessionFactory.getCurrentSession().createQuery("from Article where articleTypes_Id=?0 and id in ("+ resultList.toString().replace("[", "").replace("]", "") +")", Article.class);
		   query2.setParameter(0,id);
		   resultList2 = query2.getResultList();
		  	}  
		  return resultList2;
		 }

	@Override
	public List<Article> serchArticles(Integer pageNo, Integer recordsPerPage, String serch) {
		Session session = sessionFactory.getCurrentSession();
		Query<Article> query = session.createQuery("from Article where title like ?1 order by id desc",	Article.class);
		query.setParameter(1, "%"+serch+"%");
		List<Article> list = query.list();
		return list;
	}

	@Override
	public Long getAllRecordCounts() {
		Long count = 0L; // 必須使用 long 型態
		count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Article").getSingleResult();
		return count;
	}





}
