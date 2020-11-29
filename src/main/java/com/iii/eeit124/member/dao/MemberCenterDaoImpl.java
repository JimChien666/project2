package com.iii.eeit124.member.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
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
	public Map<String, BigDecimal> getDataPerMonth(Integer memberId) {
		//每月第一天
		Calendar cal_1=Calendar.getInstance();
		cal_1.add(Calendar.MONTH, 0);
		cal_1.set(Calendar.DAY_OF_MONTH,0);
		//每月最後一天
		Calendar cale = Calendar.getInstance();
		cal_1.add(Calendar.MONTH, 0);
		cal_1.set(Calendar.DAY_OF_MONTH,1);
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select count(o.id) from orders o where o.buyer_id=?0 and o.created_at between ?1 and ?2 and o.status !='取消'");
		query.setParameter(0, memberId);
		query.setParameter(1, cal_1.getTime());
		query.setParameter(2, cale.getTime());
		BigDecimal count = (BigDecimal)query.uniqueResult();
		Query query2 = sessionFactory.getCurrentSession().createSQLQuery("select sum(o.total) from orders o where o.buyer_id=?0 and o.created_at between ?1 and ?2 and o.status !='取消'");
		query2.setParameter(0, memberId);
		query2.setParameter(1, cal_1.getTime());
		query2.setParameter(2, cale.getTime());
		BigDecimal sum = (BigDecimal)query2.uniqueResult();
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("sum", sum);
		map.put("count", count);
		return map;
	}
}
