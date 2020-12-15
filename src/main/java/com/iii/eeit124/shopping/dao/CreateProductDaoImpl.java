package com.iii.eeit124.shopping.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.iii.eeit124.entity.AnimalTypes;
import com.iii.eeit124.entity.Categories;
import com.iii.eeit124.entity.Colors;
import com.iii.eeit124.entity.Products;

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
	
	@Override
	public List<Categories> findAllCatrgories(){
		
		@SuppressWarnings("unchecked")
		TypedQuery<Categories> query = sessionFactory.getCurrentSession().createQuery("from Categories");
		return query.getResultList();
	}

	@Override
	public List<AnimalTypes> findAllAnimalTypes() {
		@SuppressWarnings("unchecked")
		TypedQuery<AnimalTypes> query = sessionFactory.getCurrentSession().createQuery("from AnimalTypes");
		return query.getResultList();
	}

	@Override
	public Colors findOneColor(Integer id) {
		@SuppressWarnings("unchecked")
		Query<Colors> query = sessionFactory.getCurrentSession().createQuery("from Colors where id = ?0");
		query.setParameter(0, id);
		Colors color = query.uniqueResult();
		return color;

	}

	@Override
	public Categories findOneCatrgory(Integer id) {
		@SuppressWarnings("unchecked")
		Query<Categories> query = sessionFactory.getCurrentSession().createQuery("from Categories where id = ?0");
		query.setParameter(0, id);
		Categories category = query.uniqueResult();
		return category;
	}

	@Override
	public AnimalTypes findOneAnimalType(Integer id) {
		@SuppressWarnings("unchecked")
		Query<AnimalTypes> query = sessionFactory.getCurrentSession().createQuery("from AnimalTypes where id = ?0");
		query.setParameter(0, id);
		AnimalTypes animalTypes = query.uniqueResult();
		return animalTypes;
	}

	@Override
	public Products insertProduct(Products products) {
		sessionFactory.getCurrentSession().save(products);
		return products;
	}
	
	public Products selectById(Integer id) {//用ID查單筆
		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where id =?0 ",Products.class );
		query.setParameter(0, id);
		Products product = query.uniqueResult();
		return product;
	}
	
	@Override
	public Products updateProduct(Products products) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(products);
		return products;
	}
	@Override
	public Products deleteProduct(Products products) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(products);
		return products;
	}

}