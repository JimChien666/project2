package com.iii.eeit124.member.dao;

import java.util.Map;

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
	public Members getMemberById(Integer memberId) {
		Query<Members> query = sessionFactory.getCurrentSession().createQuery("from Members where ID = ?0", Members.class);
		query.setParameter(0, memberId);
		Members member = query.uniqueResult();
		return member;
	}

	@Override
	public Map<String, Double> getDataPerMonth(Integer memberId) {
		sessionFactory.getCurrentSession().createSQLQuery("select from order_items where buyer_id=?0 and created_at >= ?1 and created_at <= ?2");
		return null;
	}
}
