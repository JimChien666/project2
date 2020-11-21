package com.iii.eeit124.adopt.service;

import java.util.List;
import com.iii.eeit124.entity.Breeds;

public interface BreedsService {
	
	public Breeds create(Breeds breeds);
	
	public Breeds read(Integer breedsId);
	
	public List<Breeds> readAll();
	
	public Breeds update(Breeds breeds);
	
	public boolean delete(Integer breedsId);
	
	public List<String> readAllFamilies();
	
	public List<Breeds> readAllBreeds(String family);
	
	public List<Breeds> readDogsBreeds();
}