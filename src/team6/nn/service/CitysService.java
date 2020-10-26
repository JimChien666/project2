package team6.nn.service;

import java.util.List;

import org.hibernate.Session;

import team6.nn.dao.CitysDAO;
import team6.nn.entity.Citys;

public class CitysService implements ICitysService {
	
	private CitysDAO hDao;

	public CitysService(Session session) {
		hDao = new CitysDAO(session);
	}
	@Override
	public Citys insert(Citys bean) {		
		return hDao.insert(bean);
	}

	@Override
	public Citys select(int id) {
		return hDao.select(id);
	}

	@Override
	public List<Citys> selectAll() {
		return hDao.selectAll();
	}

	@Override
	public Citys update(int id, String name) {
		return hDao.update(id, name);
	}

	@Override
	public boolean delete(int id) {
		return hDao.delete(id);
	}

}
