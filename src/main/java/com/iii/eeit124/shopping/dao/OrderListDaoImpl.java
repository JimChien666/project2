package com.iii.eeit124.shopping.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Orders;
import com.iii.eeit124.entity.Products;

@Transactional
@Repository
public class OrderListDaoImpl implements OrderListDao {
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public List<Orders> indAllOrdersByMemberId(Integer pageNo ,Integer recordsPerPage, Integer id, Integer paidStatus, String orderStatus) {
		Integer startRecordNo = (pageNo - 1) * recordsPerPage; 
		List<Orders> list = new ArrayList<Orders>();
		String condiction = " where BUYER_ID=?0 ";
		if (paidStatus != null) {
			condiction += " and is_paid = :isPaid";
		}
		if (orderStatus != null) {
			if (!condiction.equals(" where BUYER_ID=?0 ")) {
				condiction += " and status = :status";				
			}else {
				condiction += " and status = :status";
			}
		}
		
		@SuppressWarnings("unchecked")
		TypedQuery<Orders> query = sessionFactory.getCurrentSession().createQuery("from Orders " + condiction + " order by id");
		query.setParameter(0, id).setFirstResult(startRecordNo).setMaxResults(recordsPerPage);
		if (paidStatus != null) {
			query.setParameter("isPaid", paidStatus);
		}
		if (orderStatus != null) {
			System.out.println("fuxk");
			System.out.println(orderStatus);
			query.setParameter("status", orderStatus);
		}
		query.getResultList();
		return query.getResultList();
	}


	@Override
	public Long getRecordCounts(Integer id, Integer paidStatus, String orderStatus) {
		Long count = 0L; // 必須使用 long 型態
		String condiction = " where BUYER_ID=?0 ";
		if (paidStatus != null) {
			condiction += " and is_paid = :isPaid";
		}
		if (orderStatus != null) {
			if (!condiction.equals(" where BUYER_ID=?0 ")) {
				condiction += " and status = :status";				
			}else {
				condiction += " and status = :status";
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Orders" + condiction);
		if (paidStatus != null) {
			query.setParameter("isPaid", paidStatus);
		}
		if (orderStatus != null) {
			query.setParameter("status", orderStatus);
		}
		query.setParameter(0, id).getSingleResult();
		count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	public List<Orders> indAllOrdersByMemberId(Integer pageNo ,Integer recordsPerPage, Integer id) {
		Integer startRecordNo = (pageNo - 1) * recordsPerPage; 
		@SuppressWarnings("unchecked")
		TypedQuery<Orders> query = sessionFactory.getCurrentSession().createQuery("from Orders where BUYER_ID=?0 order by id");
		query.setParameter(0, id).setFirstResult(startRecordNo).setMaxResults(recordsPerPage);
		query.getResultList();
		return query.getResultList();
	}


	@Override
	public Long getRecordCounts(Integer id) {
		Long count = 0L; // 必須使用 long 型態
		count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Orders where BUYER_ID=?0").setParameter(0, id).getSingleResult();
		return count;
	}
}
