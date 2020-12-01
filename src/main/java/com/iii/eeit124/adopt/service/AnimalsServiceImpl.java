package com.iii.eeit124.adopt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.iii.eeit124.adopt.dao.AnimalsDao;
import com.iii.eeit124.entity.Animals;

@Service("animalsService")@Lazy
public class AnimalsServiceImpl implements AnimalsService{
	
	@Autowired
	private AnimalsDao animalsDao;
	
	@Transactional
	public Animals create(Animals animals) {
		return animalsDao.create(animals);
	}
	
	public Animals read(Integer animalsId) {
		return animalsDao.read(animalsId);
	}
	
	public List<Animals> readAll(Integer memberId){
		return animalsDao.readAll(memberId);
	}
	
	public List<Animals> readMyAnimals(Integer memberId){
		return animalsDao.readMyAnimals(memberId);
	}
	
	@Transactional
	public Animals update(Animals animals) {
		return animalsDao.update(animals);
	}
	
	@Transactional
	public boolean delete(Integer animalsId) {
		return animalsDao.delete(animalsId);
	}
}
