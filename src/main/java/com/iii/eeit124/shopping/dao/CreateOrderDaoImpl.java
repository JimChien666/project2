package com.iii.eeit124.shopping.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;
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

}
