package tw.wey.old;

import java.util.List;

import org.hibernate.Session;

import wey.dao.AnimalsDao;
import wey.entity.Animals;

public class AnimalsService implements IAnimalsService{
	private AnimalsDao animalsDao;
	
	public AnimalsService(Session hSession) {
		animalsDao = new AnimalsDao(hSession);
	}
	
	@Override
	public Animals create(Animals animals) {
		return animalsDao.create(animals);
	}
	
	@Override
	public Animals select(int animalId) {
		return animalsDao.read(animalId);
	}
	
	@Override
	public List<Animals> readAll(){
		return animalsDao.readAll();
	}
}
