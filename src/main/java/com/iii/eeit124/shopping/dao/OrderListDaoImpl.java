package com.iii.eeit124.shopping.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Orders;

@Transactional
@Repository
public class OrderListDaoImpl implements OrderListDao {
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public List<Orders> indAllOrdersByMemberId(Integer id) {
		@SuppressWarnings("unchecked")
		TypedQuery<Orders> query = sessionFactory.getCurrentSession().createQuery("from Orders where BUYER_ID=?0 order by id");
		query.setParameter(0, id);
		query.getResultList();
		return query.getResultList();
	}

}
