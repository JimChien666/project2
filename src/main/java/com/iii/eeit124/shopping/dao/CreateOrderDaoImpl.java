package com.iii.eeit124.shopping.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Orders;

@Repository
@Transactional
public class CreateOrderDaoImpl implements CreateOrderDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean saveOrder(Orders order) {
		try {
			sessionFactory.getCurrentSession().save(order);
			return true;
		}catch (Exception e) {
			return false;
		} 
	}

	@Override
	public void updateOrderIsPaid(String merchantTradeNo) {
		Query query = sessionFactory.getCurrentSession().createQuery("update Orders set IS_PAID = 1 where UUID=?0");
		query.setParameter(0, merchantTradeNo);
		query.executeUpdate();
	}

	@Override
	public void updateOrderIsPaid(String merchantTradeNo, String status) {
		Query query = sessionFactory.getCurrentSession().createQuery("update Orders set IS_PAID = 2, STATUS='取消' where UUID=?0");
		query.setParameter(0, merchantTradeNo);
		query.executeUpdate();
	}

	@Override
	public void updateOrderItemStatus(String merchantTradeNo, String status) {
		Query query = sessionFactory.getCurrentSession().createQuery("update OrderItems set STATUS='取消' where ORDER_ID=(select id from Orders where UUID = ?0)");
		query.setParameter(0, merchantTradeNo);
		query.executeUpdate();
		
	}

}
