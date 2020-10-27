package nn.dao;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.query.Query;

import nn.entity.Attractions;


public class AttractionsDAO {

	private Session session;

	public AttractionsDAO(Session session) {
		this.session = session;
	}

	public Attractions insert(Attractions bean) {
		Attractions result = session.get(Attractions.class, bean.getId());

		if (result == null) {
			session.save(bean);
			return bean;
		}
		return null;
	}

	public Attractions select(int id) {//查單筆
		return session.get(Attractions.class, id);
	}

	public List<Attractions> selectAll() {//查所有,用名子排序
		Query<Attractions> query = session.createQuery("from Attractions order by id", Attractions.class);
		List<Attractions> list = query.list();
		return list;
	}

	public Attractions update(int id, String name) {
		Attractions result = session.get(Attractions.class, id);
		if (result != null) {
			result.setName(name);
		}
		return result;
	}

	public boolean delete(int id) {
		Attractions bean = session.get(Attractions.class, id);
		if (bean != null) {
			session.delete(bean);
			return true;
		}
		return false;
	}

}
