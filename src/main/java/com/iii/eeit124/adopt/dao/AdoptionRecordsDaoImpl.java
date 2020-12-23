package com.iii.eeit124.adopt.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.iii.eeit124.entity.AdoptionRecords;
import com.iii.eeit124.entity.Animals;
import com.iii.eeit124.entity.Breeds;

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
		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where MEMBER_ID=" + memberId + " and REVIEW_STATUS >= 0 order by My_Adoption_Progress_Order", AdoptionRecords.class);
		List<AdoptionRecords> list = query.list();
		return list;
	}

	@Override
	public List<AdoptionRecords> readAdoptionRecords1(String string1) {
		Session session = sessionFactory.getCurrentSession();
		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where " + string1, AdoptionRecords.class);
		List<AdoptionRecords> list = query.list();
		return list;
	}
	
	public List<AdoptionRecords> readAdoptionRecords2(String string1, String string2, String orderBy){
		Session session = sessionFactory.getCurrentSession();
		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where " + string1 + " and "+ string2 +" order by " + orderBy, AdoptionRecords.class);
		List<AdoptionRecords> list = query.list();
		return list;
	}
	
	//顯示領養申請品種map
	public Map<String, BigDecimal> readVarietyAdoptionAppliesNums(Integer memberId){
		Session session = sessionFactory.getCurrentSession();
		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where ownerMemberId=" + memberId, AdoptionRecords.class);// + " order by " + orderBy
		List<AdoptionRecords> list = query.list();
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		for (AdoptionRecords adoptionRecords : list) {
			Breeds breed = adoptionRecords.getAnimal().getBreeds();
			@SuppressWarnings("rawtypes")
			Query query1 = session.createSQLQuery("select count(ado.adoption_Id) from AdoptionRecords ado, Animals ani where ado.ownerMemberId=?0 and ado.animal_Id = ani.animal_Id and ani.breed_id = " + breed.getBreedId());
			query1.setParameter(0, memberId);
			BigDecimal num = (BigDecimal)query1.uniqueResult();
			map.put(breed.getBreed(), num);
		}
		return map;
	}
	
	//顯示領養成功品種map
	public Map<String, BigDecimal> readVarietyAdoptionSuccessedAppliesNums(Integer memberId){
		Session session = sessionFactory.getCurrentSession();
		Query<AdoptionRecords> query = session.createQuery("from AdoptionRecords where ownerMemberId=" + memberId, AdoptionRecords.class);// + " order by " + orderBy
		List<AdoptionRecords> list = query.list();
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		for (AdoptionRecords adoptionRecords : list) {
			Breeds breed = adoptionRecords.getAnimal().getBreeds();
			@SuppressWarnings("rawtypes")
			Query query1 = session.createSQLQuery("select count(ado.adoption_Id) from AdoptionRecords ado, Animals ani where ado.ownerMemberId=?0 and ado.animal_Id = ani.animal_Id and ado.review_Status = 3 and ani.breed_id = " + breed.getBreedId());
			query1.setParameter(0, memberId);
			BigDecimal num = (BigDecimal)query1.uniqueResult();
			BigDecimal num0 = new BigDecimal(0);
			if (num.compareTo(num0) > 0) {
				map.put(breed.getBreed(), num);
			}
		}
		System.out.println("readVarietyAdoptionSuccessedAppliesNums"+map);
		return map;
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
