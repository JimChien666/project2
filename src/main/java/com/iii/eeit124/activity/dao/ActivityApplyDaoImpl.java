package com.iii.eeit124.activity.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.ActivityApply;

@Transactional
@Repository
public class ActivityApplyDaoImpl implements ActivityApplyDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(ActivityApply entity) {
		sessionFactory.getCurrentSession().save(entity);
	}
	
	@Override
	public void update(ActivityApply entity) {
		sessionFactory.getCurrentSession().merge(entity);
	}
	
	@Override
	public void delete(ActivityApply entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	@Override
	public List<ActivityApply> list() {
		@SuppressWarnings("unchecked")
		TypedQuery<ActivityApply> query = sessionFactory.getCurrentSession().createQuery("from ActivityApply");
		return query.getResultList();
	}
	
	@Override
	public ActivityApply findById(Integer id) {
		@SuppressWarnings("unchecked")
		TypedQuery<ActivityApply> query = sessionFactory.getCurrentSession().createQuery("from ActivityApply e where e.id = :id");
		query.setParameter("id", id);
		List<ActivityApply> list = query.getResultList();
		return list != null ? list.get(0) : null;
	}
}
