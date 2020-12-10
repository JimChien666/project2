package com.iii.eeit124.shopping.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.OrderItems;
import com.iii.eeit124.entity.Orders;
@Repository
@Transactional
public class SellingOrderListDaoImpl implements SellingOrderListDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Orders> indAllOrdersByMemberId(Integer pageNo ,Integer recordsPerPage, Integer id, Integer paidStatus, String orderStatus) {
		
		Query q = sessionFactory.getCurrentSession().createSQLQuery("select order_id from order_items where seller_id=?0 group by order_id order by order_id desc");
		q.setParameter(0, id);
		String idList = q.getResultList().toString().replace("[", "").replace("]", "");
		System.out.println(idList);
		
		Integer startRecordNo = (pageNo - 1) * recordsPerPage; 
		List<Orders> list = new ArrayList<Orders>();
		String condiction = " where id in ("+idList+") ";
		if (paidStatus != null) {
			condiction += " and is_paid = :isPaid";
		}
		if (orderStatus != null) {
			if (!condiction.equals(" where id in ("+idList+") ")) {
				condiction += " and status = :status";				
			}else {
				condiction += " and status = :status";
			}
		}
		
		@SuppressWarnings("unchecked")
		TypedQuery<Orders> query = sessionFactory.getCurrentSession().createQuery("from Orders " + condiction + " order by id desc");
		query.setFirstResult(startRecordNo).setMaxResults(recordsPerPage);
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
		Query q = sessionFactory.getCurrentSession().createSQLQuery("select order_id from order_items where seller_id=?0 group by order_id  order by order_id desc");
		q.setParameter(0, id);
		String idList = q.getResultList().toString().replace("[", "").replace("]", "");
		System.out.println(idList);
		
		
		Long count = 0L; // 必須使用 long 型態
		String condiction = " where id in ("+idList+") ";
		if (paidStatus != null) {
			condiction += " and is_paid = :isPaid";
		}
		if (orderStatus != null) {
			if (!condiction.equals(" where id in ("+idList+") ")) {
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
		query.getSingleResult();
		count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	public List<Orders> indAllOrdersByMemberId(Integer pageNo ,Integer recordsPerPage, Integer id) {
		Query q = sessionFactory.getCurrentSession().createSQLQuery("select order_id from order_items where seller_id=?0 group by order_id  order by order_id desc");
		q.setParameter(0, id);
		String idList = q.getResultList().toString().replace("[", "").replace("]", "");
		System.out.println(idList);
		
		Integer startRecordNo = (pageNo - 1) * recordsPerPage; 
		@SuppressWarnings("unchecked")
		TypedQuery<Orders> query = sessionFactory.getCurrentSession().createQuery("from Orders where id in ("+idList+") order by id desc");
		query.setFirstResult(startRecordNo).setMaxResults(recordsPerPage);
		query.getResultList();
		return query.getResultList();
	}


	@Override
	public Long getRecordCounts(Integer id) {
		Query q = sessionFactory.getCurrentSession().createSQLQuery("select order_id from order_items where seller_id=?0 group by order_id order by order_id desc");
		q.setParameter(0, id);
		String idList = q.getResultList().toString().replace("[", "").replace("]", "");
		System.out.println(idList);
		
		Long count = 0L; // 必須使用 long 型態
		count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Orders where id in ("+idList+")").getSingleResult();
		return count;
	}


	@Override
	public Integer updateOrderStatus(Integer orderItemId) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("update order_items set status='已出貨' where id = ?0");
		query.setParameter(0, orderItemId);
		query.executeUpdate();
		
		Query queryCheck = sessionFactory.getCurrentSession().createSQLQuery("select count(id) from order_items where status!='已出貨' and order_id = (select order_id from order_items where id=?0)");
		queryCheck.setParameter(0, orderItemId);
		if(((BigDecimal)queryCheck.uniqueResult()).intValue()==0) {
			Query queryUpdateOrderStatus = sessionFactory.getCurrentSession().createSQLQuery("update orders set status='已出貨' where id = (select order_id from order_items where id = ?0)");
			queryUpdateOrderStatus.setParameter(0, orderItemId);
			queryUpdateOrderStatus.executeUpdate();
			
			Query<OrderItems> createQuery = sessionFactory.getCurrentSession().createQuery("From OrderItems where id = ?0", OrderItems.class).setParameter(0, orderItemId);
			
			return createQuery.uniqueResult().getOrder().getId();
		}
		return 0;
	}

}
