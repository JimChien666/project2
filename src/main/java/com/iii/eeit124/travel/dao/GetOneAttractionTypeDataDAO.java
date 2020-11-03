package com.iii.eeit124.travel.dao;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.iii.eeit124.entity.AttractionTypes;
import com.iii.eeit124.entity.Attractions;
import com.iii.eeit124.entity.Files;



public class GetOneAttractionTypeDataDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Attractions> selectOneTypeAttraction(int attrId) {//查所有,用名子排序
		Query<Attractions> query = sessionFactory.getCurrentSession().createQuery("from Attractions where ATTRACTION_TYPE_ID = ?0", Attractions.class);
		query.setParameter(0, attrId);
		query.setFirstResult(0);
		query.setMaxResults(3);
		List<Attractions> list = query.list();
		return list;
	}
	
	public List<AttractionTypes> selectAllAttractionTypes() {//查所有,用名子排序
		Query<AttractionTypes> query = sessionFactory.getCurrentSession().createQuery("from AttractionTypes order by id", AttractionTypes.class);
		List<AttractionTypes> list = query.list();
		return list;
	}
	
	public byte[] getFileBlob(int id) {
		Query<Files> query = sessionFactory.getCurrentSession().createQuery("from Files where ID = ?0", Files.class);
		query.setParameter(0, id);
		return query.uniqueResult().getFileBlob();
		
	}
	
}
