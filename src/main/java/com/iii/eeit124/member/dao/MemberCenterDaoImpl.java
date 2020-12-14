package com.iii.eeit124.member.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
	public Map<String, BigDecimal> getCostHistory(Integer memberId) {
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select count(o.id) from orders o where o.buyer_id=?0 and o.status !='取消'");
		query.setParameter(0, memberId);
		BigDecimal count = (BigDecimal)query.uniqueResult();
		Query query2 = sessionFactory.getCurrentSession().createSQLQuery("select sum(o.total) from orders o where o.buyer_id=?0 and o.status !='取消'");
		query2.setParameter(0, memberId);
		BigDecimal sum = (BigDecimal)query2.uniqueResult();
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		if (sum ==null) {
			sum= new BigDecimal("0");
		}
		map.put("sum", sum);
		
		map.put("count", count);
		if (count.intValue() == 0) {
			map.put("avg", new BigDecimal("0"));
		}
		else {
			map.put("avg", sum.divide(count));
		}
		return map;
	}

	@Override
	public Map<String, Object> getSellingHistory(Integer memberId) {;
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select count(id) from orders where id in (select order_id from order_items where seller_id = ?0) and  status !='取消'");
		query.setParameter(0, memberId);
		Object count = query.uniqueResult();
		Query query2 = sessionFactory.getCurrentSession().createSQLQuery("select sum(discount*price) from order_items where seller_id = ?0 and status !='取消'");
		query2.setParameter(0, memberId);
		Object sum = query2.uniqueResult();
		Map<String, Object> map = new HashMap<String, Object>();
		Query query3 = sessionFactory.getCurrentSession().createSQLQuery("select to_char(created_at,'YYYY-MM') from order_items where seller_id = ?0 and status !='取消' GROUP BY to_char(created_at,'YYYY-MM') order by to_char(created_at,'YYYY-MM')");
		query3.setParameter(0, memberId);
		List dateChooseList = query3.getResultList();
		
		map.put("sum", sum);
		map.put("count", count);
		map.put("dateChooseList", dateChooseList);
		return map;
	}

	@Override
	public Map<String, List<Object>> getSellingCountByDate(Integer memberId) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select sum(discount*price) from order_items where seller_id = ?0 and status !='取消' GROUP BY to_char(created_at,'YYYY-MM-DD') order by to_char(created_at,'YYYY-MM-DD')");
		query.setParameter(0, memberId);
		List<BigDecimal> sumList = query.getResultList();
		List<Object> result = new ArrayList<>();
		for(BigDecimal sum:sumList) {
			if (result.size()==0) {
				result.add(sum);
			}else {
				BigDecimal lastNum=sum.add((BigDecimal)result.get(result.size()-1));
				result.add(lastNum);				
			}
		}

		Query query2 = sessionFactory.getCurrentSession().createSQLQuery("select to_char(created_at,'YYYY-MM-DD') from order_items where seller_id = ?0 and status !='取消' GROUP BY to_char(created_at,'YYYY-MM-DD') order by to_char(created_at,'YYYY-MM-DD')");
		query2.setParameter(0, memberId);
		List<Object> dateList = query2.getResultList();
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		map.put("date", dateList);
		map.put("sum", result);
		map.put("sumPerDay", query.getResultList());
		return map;
	}

	@Override
	public Map<String, Object> getSellingHistory(Integer memberId, Date start, Date last) {
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select count(id) from orders where id in (select order_id from order_items where seller_id = ?0) and  status !='取消' and created_at between ?1 and ?2");
		query.setParameter(0, memberId);
		query.setParameter(1, start);
		query.setParameter(2, last);
		Object count = query.uniqueResult();
		Query query2 = sessionFactory.getCurrentSession().createSQLQuery("select sum(discount*price) from order_items where seller_id = ?0 and status !='取消' and created_at between ?1 and ?2");
		query2.setParameter(0, memberId);
		query2.setParameter(1, start);
		query2.setParameter(2, last);
		Object sum = query2.uniqueResult();
		Map<String, Object> map = new HashMap<String, Object>();
		Query query3 = sessionFactory.getCurrentSession().createSQLQuery("select to_char(created_at,'YYYY-MM') from order_items where seller_id = ?0 and status !='取消' GROUP BY to_char(created_at,'YYYY-MM') order by to_char(created_at,'YYYY-MM')");
		query3.setParameter(0, memberId);
		List dateChooseList = query3.getResultList();
		
		map.put("sum", sum);
		map.put("count", count);
		map.put("dateChooseList", dateChooseList);
		return map;
	}

	@Override
	public Map<String, List<Object>> getSellingCountByDate(Integer memberId, Date start, Date last) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select sum(discount*price) from order_items where seller_id = ?0 and status !='取消' and created_at between ?1 and ?2 GROUP BY to_char(created_at,'YYYY-MM-DD') order by to_char(created_at,'YYYY-MM-DD')");
		query.setParameter(0, memberId);
		query.setParameter(1, start);
		query.setParameter(2, last);
		List<BigDecimal> sumList = query.getResultList();
		List<Object> result = new ArrayList<>();
		for(BigDecimal sum:sumList) {
			if (result.size()==0) {
				result.add(sum);
			}else {
				BigDecimal lastNum=sum.add((BigDecimal)result.get(result.size()-1));
				result.add(lastNum);				
			}
		}

		Query query2 = sessionFactory.getCurrentSession().createSQLQuery("select to_char(created_at,'YYYY-MM-DD') from order_items where seller_id = ?0 and status !='取消' and created_at between ?1 and ?2 GROUP BY to_char(created_at,'YYYY-MM-DD') order by to_char(created_at,'YYYY-MM-DD')");
		query2.setParameter(0, memberId);
		query2.setParameter(1, start);
		query2.setParameter(2, last);
		List<Object> dateList = query2.getResultList();
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		map.put("date", dateList);
		map.put("sum", result);
		map.put("sumPerDay", query.getResultList());
		return map;
	}
}
