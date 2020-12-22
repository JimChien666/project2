package com.iii.eeit124.adopt.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.iii.eeit124.entity.AdoptionRecords;
import com.iii.eeit124.entity.Animals;

public interface AdoptionRecordsService {

	public AdoptionRecords create(AdoptionRecords entity);

	public List<AdoptionRecords> read(Integer memberId, Integer animalId);
	
	public List<AdoptionRecords> readReviewStatus(List<Integer> listAnimals);
	
	public List<AdoptionRecords> readMyAdoptionRecords(Integer memberId);
	
	public List<AdoptionRecords> readAdoptionRecords1(String string1);
	
	public List<AdoptionRecords> readAdoptionRecords2(String string1, String string2, String orderBy);
	
	public Map<String, BigDecimal> readVarietyAdoptionAppliesNums(Integer memberId);
	
	public Map<String, BigDecimal> readVarietyAdoptionSuccessedAppliesNums(Integer memberId);

	public AdoptionRecords update(AdoptionRecords entity);
}
