package team6.model;

import java.util.List;

import org.hibernate.Session;

public class HouseBeanService implements IHouseBeanService {
	
	private HouseBeanDAO hDao;

	public HouseBeanService(Session session) {
		hDao = new HouseBeanDAO(session);
	}
	@Override
	public HouseBean insert(HouseBean bean) {		
		return hDao.insert(bean);
	}

	@Override
	public HouseBean select(int houseid) {
		return hDao.select(houseid);
	}

	@Override
	public List<HouseBean> selectAll() {
		return hDao.selectAll();
	}

	@Override
	public HouseBean update(int houseid, String housename) {
		return hDao.update(houseid, housename);
	}

	@Override
	public boolean delete(int houseid) {
		return hDao.delete(houseid);
	}

}
