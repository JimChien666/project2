package team6.action;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import team6.nn.model.AttractionTypes;
import team6.nn.model.AttractionTypesDAO;
import team6.nn.model.Attractions;
import team6.nn.model.CitysDAO;
import team6.nn.model.Citys;
import team6.util.HibernateUtil;

public class InsertAttraction {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			Attractions attraction = new Attractions();
			CitysDAO cityDao = new CitysDAO(session);
			Citys city = cityDao.select(1);
			AttractionTypesDAO attractionTypeDao = new AttractionTypesDAO(session);
			AttractionTypes attractionType = attractionTypeDao.select(1);
			attraction.setId(100);
			attraction.setCity(city);
			attraction.setAttractionType(attractionType);
			Set<Attractions> attractions = new HashSet<Attractions>();
			attractions.add(attraction);
			city.setAttractions(attractions);
			attractionType.setAttractions(attractions);
			attraction.setCity(city);
			attraction.setAttractionType(attractionType);
			
			session.save(city);
			session.save(attractionType);
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			System.out.println("session closed");
			HibernateUtil.closeSessionFactory();
		}
		
		
		
	}
}
