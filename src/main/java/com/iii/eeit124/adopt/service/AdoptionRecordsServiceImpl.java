package com.iii.eeit124.adopt.service;

import java.util.List;

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

	@Override
	public AdoptionRecords update(AdoptionRecords entity) {
		return adoptionRecordsDao.update(entity);
	}

}
