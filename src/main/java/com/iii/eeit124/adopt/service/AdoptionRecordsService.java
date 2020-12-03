package com.iii.eeit124.adopt.service;

import java.util.List;

import com.iii.eeit124.entity.AdoptionRecords;

public interface AdoptionRecordsService {

	public AdoptionRecords create(AdoptionRecords entity);

	public List<AdoptionRecords> read(Integer memberId, Integer animalId);
}
