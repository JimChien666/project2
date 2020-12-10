package com.iii.eeit124.article.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iii.eeit124.entity.FollowedArticle;

@Repository
public class FollowedDAOImpl implements FollowedDAO {

	@Autowired(required = false)
	private SessionFactory sessionFactory;
	
	@Override
	public FollowedArticle statusCheck(Integer memberid, Integer articleid) {
		Session session = sessionFactory.getCurrentSession();
		Query<FollowedArticle> query = session.createQuery("from FollowedArticle where memeberid = ?1 and articleid = ?2");
		query.setParameter(1, memberid);
		query.setParameter(2, articleid);
//		FollowedArticle followedArticle = new FollowedArticle();
		FollowedArticle statusResult = query.uniqueResult();
//		System.out.println("DAOresult:"+statusResult);
		return statusResult;
			
//		return query.uniqueResult().getStatus();
	}

	@Override
	public void statusSave(FollowedArticle followedArticle) {
		sessionFactory.getCurrentSession().save(followedArticle);				
	}

	@Override
	public void statusUpdate(FollowedArticle followedArticle) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update FollowedArticle f set f.status = ?1 where id = ?2");
		Integer memberid = followedArticle.getMemberid();
		Integer id = followedArticle.getId();
		query.setParameter(1, memberid);
		query.setParameter(2, id);
		sessionFactory.getCurrentSession().update(followedArticle);	
	}

	@Override
	public Integer checkId(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query<FollowedArticle> query = session.createQuery("from FollowedArticle where id = ?1");
		query.setParameter(1, id);
		return null;
	}

	@Override
	public List<FollowedArticle> statusList(Integer memberid) {
		Session session = sessionFactory.getCurrentSession();
		Query<FollowedArticle> query = session.createQuery("from FollowedArticle where memeberid = ?1 order by id desc");
		query.setParameter(1, memberid);
		List<FollowedArticle> list = query.list();
		return list;
	}
}
