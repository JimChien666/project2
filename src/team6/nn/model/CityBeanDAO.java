package team6.nn.model;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.query.Query;

public class CityBeanDAO {

	private Session session;

	public CityBeanDAO(Session session) {
		this.session = session;
	}

	public CityBean insert(CityBean bean) {
		CityBean result = session.get(CityBean.class, bean.getId());

		if (result == null) {
			session.save(bean);
			return bean;
		}
		return null;
	}

	public CityBean select(int id) {//查單筆
		return session.get(CityBean.class, id);
	}

	public List<CityBean> selectAll() {//查所有,用名子排序
		Query<CityBean> query = session.createQuery("from HouseBean order by housename", CityBean.class);
		List<CityBean> list = query.list();
		return list;
	}

	public CityBean update(int id, String name) {
		CityBean result = session.get(CityBean.class, id);
		if (result != null) {
			result.setName(name);
		}
		return result;
	}

	public boolean delete(int id) {
		CityBean bean = session.get(CityBean.class, id);
		if (bean != null) {
			session.delete(bean);
			return true;
		}
		return false;
	}

}
