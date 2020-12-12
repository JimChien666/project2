package com.iii.eeit124.article.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iii.eeit124.entity.MemberOption;
@Repository
public class MemberOptionDAOImpl implements MemberOptionDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(MemberOption entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

}
