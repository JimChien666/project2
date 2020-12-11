package com.iii.eeit124.adopt.service;

import java.util.List;

import com.iii.eeit124.entity.AdoptionRecords;
import com.iii.eeit124.entity.Animals;

public interface AdoptionRecordsService {

	public AdoptionRecords create(AdoptionRecords entity);

	public List<AdoptionRecords> read(Integer memberId, Integer animalId);
	
	public List<AdoptionRecords> readReviewStatus(List<Integer> listAnimals);
	
	public List<AdoptionRecords> readMyAdoptionRecords(Integer memberId);
	
	public List<AdoptionRecords> readAdoptionRecords2(String string1, String string2);

	public AdoptionRecords update(AdoptionRecords entity);
}
