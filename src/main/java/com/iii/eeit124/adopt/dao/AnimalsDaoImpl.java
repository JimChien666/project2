package com.iii.eeit124.adopt.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.iii.eeit124.entity.Animals;

@Repository("animalsDao")@Lazy
public class AnimalsDaoImpl implements AnimalsDao{
	
	@Autowired
	private SessionFactory sessionFactory;//Hibernate SessionFactory

	@Transactional
	public Animals create(Animals entity) {
		Session session = sessionFactory.getCurrentSession();
//		Animals result = session.get(Animals.class, entity.getAnimalId());//TODO id會出錯誤訊息id to load is required for loading
//		if (result == null) {
			session.save(entity);
			return entity;
//		}
//		return null;
	}
	
	public Animals read(Integer animalsId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Animals.class, animalsId);
	}
	
	public List<Animals> readAll(){
		Session session = sessionFactory.getCurrentSession();
		Query<Animals> query = session.createQuery("from Animals order by animal_id desc", Animals.class);
		List<Animals> list = query.list();
		return list;
	}
	
	@Transactional
	public Animals update(Animals entity) {
		Session session = sessionFactory.getCurrentSession();
//		Animals result = session.get(Animals.class, entity.getAnimalId());
//		if (result != null) {
			session.update(entity);
			return entity;
//		}
//		return null;
	}
	
	@Transactional
	public boolean delete(Integer animalsId) {
		Session session = sessionFactory.getCurrentSession();
		Animals result = session.get(Animals.class, animalsId);
		if (result != null) {
			session.delete(result);
			return true;
		}
		return false;
	}
}
