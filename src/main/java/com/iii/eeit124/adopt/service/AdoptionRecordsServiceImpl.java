package com.iii.eeit124.adopt.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.iii.eeit124.adopt.dao.AdoptionRecordsDao;
import com.iii.eeit124.entity.AdoptionRecords;

@Service("adoptionRecordsService")@Lazy
public class AdoptionRecordsServiceImpl implements AdoptionRecordsService{
	
	@Autowired
	private AdoptionRecordsDao adoptionRecordsDao;
	
	@Transactional
	public AdoptionRecords create(AdoptionRecords entity) {
		return adoptionRecordsDao.create(entity);
	}

	@Override
	public List<AdoptionRecords> read(Integer memberId, Integer animalId) {
		return adoptionRecordsDao.read(memberId, animalId);
	}
	
	public List<AdoptionRecords> readReviewStatus(List<Integer> listAnimals){
		return adoptionRecordsDao.readReviewStatus(listAnimals);
	}
	
	public List<AdoptionRecords> readMyAdoptionRecords(Integer memberId){
		return adoptionRecordsDao.readMyAdoptionRecords(memberId);
	}

	public List<AdoptionRecords> readAdoptionRecords1(String string1){
		return adoptionRecordsDao.readAdoptionRecords1(string1);
	}

	public List<AdoptionRecords> readAdoptionRecords2(String string1, String string2, String orderBy){
		return adoptionRecordsDao.readAdoptionRecords2(string1, string2, orderBy);
	}
	
	public Map<String, BigDecimal> readVarietyAdoptionAppliesNums(Integer memberId){
		return adoptionRecordsDao.readVarietyAdoptionAppliesNums(memberId);
	}
	
	public Map<String, BigDecimal> readVarietyAdoptionSuccessedAppliesNums(Integer memberId){
		return adoptionRecordsDao.readVarietyAdoptionSuccessedAppliesNums(memberId);
	}
	
	@Override
	public AdoptionRecords update(AdoptionRecords entity) {
		return adoptionRecordsDao.update(entity);
	}

}
