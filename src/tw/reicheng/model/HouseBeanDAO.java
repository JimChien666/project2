package tw.reicheng.model;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.query.Query;

public class HouseBeanDAO {

	private Session session;

	public HouseBeanDAO(Session session) {
		this.session = session;
	}

	public HouseBean insert(HouseBean bean) {
		HouseBean result = session.get(HouseBean.class, bean.getHouseid());

		if (result == null) {
			session.save(bean);
			return bean;
		}
		return null;
	}

	public HouseBean select(int houseid) {//查單筆
		return session.get(HouseBean.class, houseid);
	}

	public List<HouseBean> selectAll() {//查所有,用名子排序
		Query<HouseBean> query = session.createQuery("from HouseBean order by housename", HouseBean.class);
		List<HouseBean> list = query.list();
		return list;
	}

	public HouseBean update(int houseid, String housename) {
		HouseBean result = session.get(HouseBean.class, houseid);
		if (result != null) {
			result.setHousename(housename);
		}
		return result;
	}

	public boolean delete(int houseid) {
		HouseBean bean = session.get(HouseBean.class, houseid);
		if (bean != null) {
			session.delete(bean);
			return true;
		}
		return false;
	}

}
