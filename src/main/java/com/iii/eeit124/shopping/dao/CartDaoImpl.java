package com.iii.eeit124.shopping.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.CartItems;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public CartItems getCartItemInfo(Integer productId) {
		sessionFactory.getCurrentSession().createQuery("from Products");
		return null;
	}

}
