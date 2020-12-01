package com.iii.eeit124.adopt.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.iii.eeit124.entity.AdoptionRecords;

@Repository("adoptionRecordsDao")
@Lazy
public class AdoptionRecordsDaoImpl implements AdoptionRecordsDao {

	@Autowired
	private SessionFactory sessionFactory;// Hibernate SessionFactory

	public AdoptionRecords create(AdoptionRecords entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	//TODO 不應該抓領養紀錄的動物，要抓動物的
	@Override
	public List<AdoptionRecords> read(Integer memberId) {
		Session session = sessionFactory.getCurrentSession();
		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where MEMBER_ID=" + memberId + " order by ADOPTION_ID desc", AdoptionRecords.class);
		List<AdoptionRecords> list = query.list();
		return list;
	}

}
