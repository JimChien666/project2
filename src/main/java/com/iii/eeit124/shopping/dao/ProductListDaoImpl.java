package com.iii.eeit124.shopping.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Activitys;
import com.iii.eeit124.entity.Products;

@Transactional
@Repository
public class ProductListDaoImpl implements ProductListDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Products> findAllProducts() {
		@SuppressWarnings("unchecked")
		TypedQuery<Products> query = sessionFactory.getCurrentSession().createQuery("from Products");
		return query.getResultList();
	}

}
