package team6.nn.service;

import java.util.List;

import org.hibernate.Session;

import team6.nn.dao.AttractionCommentsDAO;
import team6.nn.dao.AttractionsDAO;
import team6.nn.entity.AttractionComments;
import team6.nn.entity.Attractions;

public class AttractionCommentsService implements IAttractionCommentsService {
	
	private AttractionCommentsDAO attractionCommentsDao;

	public AttractionCommentsService(Session session) {
		attractionCommentsDao = new AttractionCommentsDAO(session);
	}
	@Override
	public AttractionComments insert(AttractionComments bean) {		
		return attractionCommentsDao.insert(bean);
	}

	@Override
	public AttractionComments select(int id) {
		return attractionCommentsDao.select(id);
	}

	@Override
	public List<AttractionComments> selectAll() {
		return attractionCommentsDao.selectAll();
	}

	@Override
	public AttractionComments update(int id, String name) {
		return attractionCommentsDao.update(id, name);
	}

	@Override
	public boolean delete(int id) {
		return attractionCommentsDao.delete(id);
	}

}
