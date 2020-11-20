package com.iii.eeit124.adopt.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.iii.eeit124.entity.Breeds;

@Repository("BreedsDao")
public class BreedsDaoImpl implements BreedsDao{

	@Autowired
	private SessionFactory sessionFactory;// Hibernate SessionFactory
	
	public Breeds create(Breeds entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
		return entity;
	}
	
	public Breeds read(Integer breedsId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Breeds.class, breedsId);
	}
	
	public List<Breeds> readAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Breeds> query = session.createQuery("from Breeds", Breeds.class);
		List<Breeds> list = query.list();
		return list;
	}
	
	public List<String> readAllFamilies() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<String> query = session.createQuery("select distinct family from Breeds");
		List<String> list = query.list();
		return list;
	}
	
	public List<Breeds> readDogsBreeds() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Breeds> query = session.createQuery("from Breeds where family='狗' order by BREED_ID");
		List<Breeds> list = query.list();
		return list;
	}
	
	public List<Breeds> readAllBreeds(String familyValue) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Breeds> query = session.createQuery("from Breeds where family='"+familyValue+"' order by BREED_ID");
		List<Breeds> list = query.list();
		return list;
	}
	//還無法用
//	public List<String> readAllBreeds(String familyValue) {
//		Session session = sessionFactory.getCurrentSession();
//		System.out.println(11111);
//		@SuppressWarnings("unchecked")
//		Query<String> query = session.createQuery("from Breeds where family='"+familyValue+"'");
////		Query<String> query = session.createQuery("select breed, BREED_ID from Breeds where family='"+familyValue+"'");
//		System.out.println(22222);
//		List<String> list = query.list();
//		System.out.println("33333"+list);
//		return list;
//	}
	//可用
//	public List<String> readAllBreeds(String familyValue) {
//		Session session = sessionFactory.getCurrentSession();
//		@SuppressWarnings("unchecked")
//		TypedQuery<String> query = session.createQuery("select distinct breed from Breeds where family='"+familyValue+"'");
//		System.out.println("query"+query);
//		return query.getResultList();
//	}
	
	public Breeds update(Breeds entity) {// entity為更新的內容
		Session session = sessionFactory.getCurrentSession();
		session.merge(entity);
		return entity;
	}

	public boolean delete(Integer breedsId) {
		Session session = sessionFactory.getCurrentSession();
		Breeds result = session.get(Breeds.class, breedsId);
		if (result != null) {
			session.delete(result);
			return true;
		}
		return false;
	}
}
