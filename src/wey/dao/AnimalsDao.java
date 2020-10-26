package wey.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import wey.entity.Animals;

public class AnimalsDao {
	private Session hSession;
	public AnimalsDao(Session hSession) {
		this.hSession = hSession;
	}
	
	public Animals create(Animals animals) {
		Animals result = hSession.get(Animals.class, animals.getAnimalId());
		if (result == null) {
			hSession.save(animals);
			return animals;
		}
		return null;
	}
	
	public Animals read(int animalsId) {
		return hSession.get(Animals.class, animalsId);
	}
	
	public List<Animals> readAll(){
		Query<Animals> query = hSession.createQuery("from Animals order by animal_id desc", Animals.class);
		List<Animals> list = query.list();
		return list;
	}
	//test10260003 TODO
//	public Animals update() {
//		
//	}
	
	public boolean delete(int animalsId) {
		Animals result = hSession.get(Animals.class, animalsId);
		if (result!=null) {
			hSession.delete(result);
			return true;
		}
		return false;
	}
}
