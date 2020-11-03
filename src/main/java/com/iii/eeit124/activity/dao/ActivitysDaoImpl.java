package com.iii.eeit124.activity.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Activitys;

@Transactional
@Repository
public class ActivitysDaoImpl implements ActivitysDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Activitys entity) {
		sessionFactory.getCurrentSession().save(entity);
	}
	
	@Override
	public void update(Activitys entity) {
		sessionFactory.getCurrentSession().update(entity);
	}
	
	@Override
	public void delete(Activitys entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public List<Activitys> list() {
		@SuppressWarnings("unchecked")
		TypedQuery<Activitys> query = sessionFactory.getCurrentSession().createQuery("from Activitys");
		return query.getResultList();
	}

	@Override
	public Activitys findById(Integer id) {
		@SuppressWarnings("unchecked")
		TypedQuery<Activitys> query = sessionFactory.getCurrentSession().createQuery("from Activitys e where e.id = :id");
		query.setParameter("id", id);
		List<Activitys> list = query.getResultList();
		return list != null ? list.get(0) : null;
	}

}
