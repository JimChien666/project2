package team6.nn.service;

import java.util.List;

import org.hibernate.Session;

import team6.nn.dao.AttractionTypesDAO;
import team6.nn.entity.AttractionTypes;

public class AttractionTypesService implements IAttractionTypesService {
	
	private AttractionTypesDAO attractionTypesDao;

	public AttractionTypesService(Session session) {
		attractionTypesDao = new AttractionTypesDAO(session);
	}
	@Override
	public AttractionTypes insert(AttractionTypes bean) {		
		return attractionTypesDao.insert(bean);
	}

	@Override
	public AttractionTypes select(int id) {
		return attractionTypesDao.select(id);
	}

	@Override
	public List<AttractionTypes> selectAll() {
		return attractionTypesDao.selectAll();
	}

	@Override
	public AttractionTypes update(int id, String name) {
		return attractionTypesDao.update(id, name);
	}

	@Override
	public boolean delete(int id) {
		return attractionTypesDao.delete(id);
	}

}
