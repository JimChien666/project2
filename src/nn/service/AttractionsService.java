package nn.service;

import java.util.List;

import org.hibernate.Session;

import nn.dao.AttractionsDAO;
import nn.entity.Attractions;


public class AttractionsService implements IAttractionsService {
	
	private AttractionsDAO attractionsDao;

	public AttractionsService(Session session) {
		attractionsDao = new AttractionsDAO(session);
	}
	@Override
	public Attractions insert(Attractions bean) {		
		return attractionsDao.insert(bean);
	}

	@Override
	public Attractions select(int id) {
		return attractionsDao.select(id);
	}

	@Override
	public List<Attractions> selectAll() {
		return attractionsDao.selectAll();
	}

	@Override
	public Attractions update(int id, String name) {
		return attractionsDao.update(id, name);
	}

	@Override
	public boolean delete(int id) {
		return attractionsDao.delete(id);
	}

}
