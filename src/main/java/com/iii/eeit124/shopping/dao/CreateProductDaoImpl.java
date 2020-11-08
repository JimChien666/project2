package com.iii.eeit124.shopping.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Colors;

@Transactional
@Repository
public class CreateProductDaoImpl implements CreateProductDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Colors> findAllColors() {
		@SuppressWarnings("unchecked")
		TypedQuery<Colors> query = sessionFactory.getCurrentSession().createQuery("from Colors");
		return query.getResultList();
	}

}
