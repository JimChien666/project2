package com.iii.eeit124.shopping.dao;


import java.util.Optional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.CartItems;
import com.iii.eeit124.entity.Products;

import oracle.net.aso.q;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public CartItems getCartItemInfo(Integer productId) {
		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where ID = ?0", Products.class);
		query.setParameter(0, productId);
		Products product = query.uniqueResult();
		CartItems cartItem = new CartItems();
		cartItem.setProductId(productId);
		cartItem.setProductName(product.getName());
		cartItem.setDiscount(product.getDiscount());
		cartItem.setPrice(product.getPrice());
		cartItem.setMemberId(product.getMemberId());
		cartItem.setQuantity(0);
		cartItem.setMemberName(product.getMemberName());
		return cartItem;
	}

	@Override
	public Products getProduct(Integer productId) {
		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where ID = ?0", Products.class);
		query.setParameter(0, productId);
		Products product = query.uniqueResult();
		return product;
	}

}
