package com.iii.eeit124.adopt.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.iii.eeit124.entity.AdoptionRecords;
import com.iii.eeit124.entity.Animals;

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

	@Override//不能list，只能一筆
	public List<AdoptionRecords> read(Integer memberId, Integer animalId) {
		Session session = sessionFactory.getCurrentSession();
		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where MEMBER_ID=" + memberId + " and ANIMAL_ID=" + animalId + "order by ADOPTION_ID", AdoptionRecords.class);
		List<AdoptionRecords> list = query.list();
		return list;
	}

	@Override//AdoptionRequestList
	public List<AdoptionRecords> readReviewStatus(List<Integer> listAnimals) {
		Session session = sessionFactory.getCurrentSession();
		String string = listAnimals.toString().replace("[", "(").replace("]", ")");
		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where Animal_Id in " + string, AdoptionRecords.class);
		List<AdoptionRecords> list = query.list();
		return list;
	}
	
	public List<AdoptionRecords> readMyAdoptionRecords(Integer memberId){
		Session session = sessionFactory.getCurrentSession();
		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where MEMBER_ID=" + memberId + "order by ADOPTION_ID", AdoptionRecords.class);
		List<AdoptionRecords> list = query.list();
		return list;
	}
	
	public List<AdoptionRecords> readAdoptionRecords2(String string1, String string2, String orderBy){
		Session session = sessionFactory.getCurrentSession();
		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where " + string1 + "and "+ string2 +"order by " + orderBy, AdoptionRecords.class);
		List<AdoptionRecords> list = query.list();
		return list;
	}
	
//	public List<AdoptionRecords> readAdoptionRecords1(String string1, String orderBy){
//		Session session = sessionFactory.getCurrentSession();
//		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where " + string1 + "and "+ string2 +"order by ADOPTION_ID", AdoptionRecords.class);
//		List<AdoptionRecords> list = query.list();
//		return list;
//		
//	}

	@Override
	public AdoptionRecords update(AdoptionRecords entity) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(entity);
		return entity;
	}

}
