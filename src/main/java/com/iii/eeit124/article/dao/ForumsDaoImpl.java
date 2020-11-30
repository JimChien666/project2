package com.iii.eeit124.article.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

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
	public void save(Forums forums) {
		sessionFactory.getCurrentSession().save(forums);
	}

	@Override
	public void saveArticle(Article article) {
		sessionFactory.getCurrentSession().save(article);

	}

	@Override
	public void updateArticle(Article article) {
		sessionFactory.getCurrentSession().merge(article);
	}

	@Override
	public Forums selectForum(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Forums> query = session.createQuery("from Forums where id = ?1", Forums.class);
		query.setParameter(1, id);
		return query.uniqueResult();
	}

	@Override
	public List<Forums> select(Integer pageNo, Integer recordsPerPage, Integer id) {
		Integer startRecordNo = (pageNo - 1) * recordsPerPage; 
		@SuppressWarnings("unchecked")
		TypedQuery<Forums> query = sessionFactory.getCurrentSession().createQuery("from Forums where article_id=?0 order by id");
		query.setParameter(0, id).setFirstResult(startRecordNo).setMaxResults(recordsPerPage);
		query.getResultList();
		return query.getResultList();
	}

	@Override
	public Long getRecordCounts(Integer id) {
		Long count = 0L; // 必須使用 long 型態
		count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Forums where article_id=?0").setParameter(0, id).getSingleResult();
		return count;
	}

	@Override
	public List<Forums> selectForumById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Forums> query = session.createQuery("from Forums where article_id = ?1 order by id", Forums.class);
		query.setParameter(1, id);
		return query.getResultList();
	}

}
