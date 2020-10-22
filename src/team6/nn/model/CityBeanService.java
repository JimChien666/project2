package team6.nn.model;

import java.util.List;

import org.hibernate.Session;

public class CityBeanService implements ICityBeanService {
	
	private CityBeanDAO hDao;

	public CityBeanService(Session session) {
		hDao = new CityBeanDAO(session);
	}
	@Override
	public CityBean insert(CityBean bean) {		
		return hDao.insert(bean);
	}

	@Override
	public CityBean select(int houseid) {
		return hDao.select(houseid);
	}

	@Override
	public List<CityBean> selectAll() {
		return hDao.selectAll();
	}

	@Override
	public CityBean update(int houseid, String housename) {
		return hDao.update(houseid, housename);
	}

	@Override
	public boolean delete(int houseid) {
		return hDao.delete(houseid);
	}

}
