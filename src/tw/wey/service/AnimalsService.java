package tw.wey.service;

import java.util.List;

import org.springframework.stereotype.Service;
import tw.wey.dao.AnimalsDao;
import tw.wey.entity.Animals;

@Service
public class AnimalsService implements IAnimalsService{
	
	private AnimalsDao animalsDao;
	
	public AnimalsService(AnimalsDao animalsDao) {
		this.animalsDao = animalsDao;
	}
	
	public Animals create(Animals animals) {
		return animalsDao.create(animals);
	}
	
	public Animals read(int animalsId) {
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
