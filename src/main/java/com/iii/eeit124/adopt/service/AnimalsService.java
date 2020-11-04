package com.iii.eeit124.adopt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.iii.eeit124.adopt.dao.AnimalsDao;
import com.iii.eeit124.entity.Animals;

@Service("animalsService")@Lazy
public class AnimalsService implements IAnimalsService{
	
	private AnimalsDao animalsDao;
	
	@Autowired
	public AnimalsService(@Qualifier("animalsDao")AnimalsDao animalsDao) {
		this.animalsDao = animalsDao;
	}
	
	public Animals create(Animals animals) {
		return animalsDao.create(animals);
	}
	
	public Animals read(String animalsId) {
		return animalsDao.read(animalsId);
	}
	
	public List<Animals> readAll(){
		return animalsDao.readAll();
	}
	
	public Animals update(Animals animals) {
		return animalsDao.update(animals);
	}
	
	public boolean delete(int animalsId) {
		return animalsDao.delete(animalsId);
	}
}
