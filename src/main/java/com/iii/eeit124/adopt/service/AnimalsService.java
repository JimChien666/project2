package com.iii.eeit124.adopt.service;

import java.util.List;
import com.iii.eeit124.entity.Animals;

public interface AnimalsService {
	
	public Animals create(Animals animals);
	
	public Animals read(Integer animalsId);
	
	public List<Animals> readAll(Integer memberId);
	
	public List<Animals> readMyAnimals(Integer memberId);
	
	public Animals update(Animals animals);
	
	public boolean delete(Integer animalsId);
	
}