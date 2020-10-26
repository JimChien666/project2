package team6.nn.action;


import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;


import team6.nn.dao.InsertAttractionDAO;
import team6.nn.dao.TagsDAO;
import team6.nn.entity.AttractionTypes;
import team6.nn.entity.Attractions;
import team6.nn.entity.Citys;
import team6.nn.entity.Files;
import team6.nn.entity.Tags;

public class InsertAttractionAction {
	Session session;
	public InsertAttractionAction(Session session) {
		this.session=session;
	}
	public void InsertAttraction(int cityId, int attractionTypesId, int ... tagIds) {
Attractions attraction = new Attractions();
		
		//選擇city
		InsertAttractionDAO insertAttractionDAO = new InsertAttractionDAO(session); 
		Citys city = insertAttractionDAO.selectCity(cityId);
		//選擇Attractiontypes

		AttractionTypes attractionType = insertAttractionDAO.selectAttractionType(attractionTypesId);
		attraction.setId(100);
		attraction.setCity(city);
		attraction.setAttractionType(attractionType);
		Set<Attractions> attractions = new HashSet<Attractions>();
		attractions.add(attraction);
		city.setAttractions(attractions);
		attractionType.setAttractions(attractions);
		attraction.setCity(city);
		attraction.setAttractionType(attractionType);
		
		TagsDAO tagsDAO = new TagsDAO(session);
		Set<Tags> tags = new HashSet<Tags>();
		for (int tagId:tagIds) {
			tags.add(tagsDAO.select(tagId));
		}
		attraction.setTags(tags);
		Files file = new Files();
		Set<Files> files = new HashSet<Files>();
		files.add(file);
		attraction.setFiles(files);
		
		session.save(attraction);
	}
}
