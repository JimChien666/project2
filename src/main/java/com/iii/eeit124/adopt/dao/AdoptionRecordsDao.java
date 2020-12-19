package com.iii.eeit124.adopt.dao;

import java.util.List;

import com.iii.eeit124.entity.AdoptionRecords;
import com.iii.eeit124.entity.Animals;

public interface AdoptionRecordsDao {

	public AdoptionRecords create(AdoptionRecords entity);

	public List<AdoptionRecords> read(Integer memberId, Integer animalId);
	
	public List<AdoptionRecords> readReviewStatus(List<Integer> listAnimals);
	
	public List<AdoptionRecords> readMyAdoptionRecords(Integer memberId);
	
	List<AdoptionRecords> readAdoptionRecords1(String string1);
	
	public List<AdoptionRecords> readAdoptionRecords2(String string1, String string2, String orderBy);

	public AdoptionRecords update(AdoptionRecords entity);

}
