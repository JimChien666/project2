package team6.nn.model;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.query.Query;

public class CitysDAO {

	private Session session;

	public CitysDAO(Session session) {
		this.session = session;
	}

	public Citys insert(Citys bean) {
		Citys result = session.get(Citys.class, bean.getId());

		if (result == null) {
			session.save(bean);
			return bean;
		}
		return null;
	}

	public Citys select(int id) {//查單筆
		return session.get(Citys.class, id);
	}

	public List<Citys> selectAll() {//查所有,用名子排序
		Query<Citys> query = session.createQuery("from Citys order by id", Citys.class);
		List<Citys> list = query.list();
		return list;
	}

	public Citys update(int id, String name) {
		Citys result = session.get(Citys.class, id);
		if (result != null) {
			result.setName(name);
		}
		return result;
	}

	public boolean delete(int id) {
		Citys bean = session.get(Citys.class, id);
		if (bean != null) {
			session.delete(bean);
			return true;
		}
		return false;
	}

}
