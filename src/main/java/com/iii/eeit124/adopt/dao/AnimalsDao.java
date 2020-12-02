package com.iii.eeit124.adopt.dao;

import java.util.List;
import com.iii.eeit124.entity.Animals;

public interface AnimalsDao {
	
	public Animals create(Animals entity);
	
	public Animals read(Integer animalsId);
	
	public List<Animals> readAll();
	
	public List<Animals> readMyAnimals(Integer memberId);
	
	public Animals update(Animals entity);
	
	public boolean delete(Integer animalsId);
	
}
