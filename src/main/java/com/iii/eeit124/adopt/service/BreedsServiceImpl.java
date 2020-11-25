package com.iii.eeit124.adopt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.iii.eeit124.adopt.dao.BreedsDao;
import com.iii.eeit124.entity.Breeds;

@Service("breedsService")
public class BreedsServiceImpl implements BreedsService {
	
	@Autowired
	private BreedsDao breedsDao;

	@Transactional
	public Breeds create(Breeds entity) {
		return breedsDao.create(entity);
	}

	public Breeds read(Integer breedsId) {
		return breedsDao.read(breedsId);
	}

	public List<Breeds> readAll() {
		return breedsDao.readAll();
	}

	@Transactional
	public Breeds update(Breeds entity) {
		return breedsDao.update(entity);
	}

	@Transactional
	public boolean delete(Integer breedsId) {
		return breedsDao.delete(breedsId);
	}

	public List<String> readAllFamilies(){
		return breedsDao.readAllFamilies();
	}
	
	public List<Breeds> readAllBreeds(String family){
		return breedsDao.readAllBreeds(family);
	}
	
	public List<Breeds> readDogsBreeds(){
		return breedsDao.readDogsBreeds();
	}
	
	public List<Breeds> readBreed(String breedText){
		return breedsDao.readBreed(breedText);
	}
	
	public List<String> readFamily(Integer animalId){
		return breedsDao.readFamily(animalId);
	}
}
