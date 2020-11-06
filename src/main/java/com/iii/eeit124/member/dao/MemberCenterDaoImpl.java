package com.iii.eeit124.member.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Members;
@Transactional
@Repository
public class MemberCenterDaoImpl implements MemberCenterDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Nullable
	public Members getMemberById(int id) {
		Query<Members> query = sessionFactory.getCurrentSession().createQuery("from Members where ID = ?0", Members.class);
		query.setParameter(0, id);
		Members member = query.uniqueResult();
		return member;
	}
}
