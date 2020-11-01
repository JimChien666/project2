package tw.wey.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import tw.wey.entity.Animals;

@Repository("animalsDao")
public class AnimalsDao {
	
	private SessionFactory hSessionFactory;//Hibernate SessionFactory
	
	@Autowired
	public AnimalsDao(@Qualifier("sessionFactory") SessionFactory hSessionFactory) {
		this.hSessionFactory = hSessionFactory;
	}
	
	public Animals create(Animals animals) {
		Session hSession = hSessionFactory.getCurrentSession();
		Animals result = hSession.get(Animals.class, animals.getAnimalId());
		if (result == null) {
			hSession.save(animals);
			return animals;
		}
		return null;
	}
	
	public Animals read(int animalsId) {
		Session hSession = hSessionFactory.getCurrentSession();
		return hSession.get(Animals.class, animalsId);
	}
	
	public List<Animals> readAll(){
		Session hSession = hSessionFactory.getCurrentSession();
		Query<Animals> query = hSession.createQuery("from Animals order by animal_id desc", Animals.class);
		List<Animals> list = query.list();
		return list;
	}
	
	public Animals update(Animals animals) {
		Session hSession = hSessionFactory.getCurrentSession();
		Animals result = hSession.get(Animals.class, animals.getAnimalId());
		if (result == null) {
			hSession.update(animals);
			return animals;
		}
		return null;
	}
	
	public boolean delete(int animalsId) {
		Session hSession = hSessionFactory.getCurrentSession();
		Animals result = hSession.get(Animals.class, animalsId);
		if (result!=null) {
			hSession.delete(result);
			return true;
		}
		return false;
	}
}
