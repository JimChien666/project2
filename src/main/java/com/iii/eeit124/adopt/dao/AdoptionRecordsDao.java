package com.iii.eeit124.adopt.dao;

import java.util.List;

import com.iii.eeit124.entity.AdoptionRecords;

public interface AdoptionRecordsDao {

	public AdoptionRecords create(AdoptionRecords entity);

	public List<AdoptionRecords> read(Integer memberId, Integer animalId);
}
