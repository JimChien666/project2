package com.iii.eeit124.adopt.service;

import java.util.List;

import com.iii.eeit124.entity.AdoptionRecords;
import com.iii.eeit124.entity.Animals;

public interface AdoptionRecordsService {

	public AdoptionRecords create(AdoptionRecords entity);

	public List<AdoptionRecords> read(Integer memberId, Integer animalId);
	
	public List<AdoptionRecords> readReviewStatus(Integer memberId, Integer animalId, Integer reviewStatus, List<Integer> listAnimals);

	public AdoptionRecords update(AdoptionRecords entity);
}
