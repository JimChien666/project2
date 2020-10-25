package team6.nn.dao;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.query.Query;

import team6.nn.entity.AttractionTypes;

public class AttractionTypesDAO {

	private Session session;

	public AttractionTypesDAO(Session session) {
		this.session = session;
	}

	public AttractionTypes insert(AttractionTypes bean) {
		AttractionTypes result = session.get(AttractionTypes.class, bean.getId());

		if (result == null) {
			session.save(bean);
			return bean;
		}
		return null;
	}

	public AttractionTypes select(int id) {//查單筆
		return session.get(AttractionTypes.class, id);
	}

	public List<AttractionTypes> selectAll() {//查所有,用名子排序
		Query<AttractionTypes> query = session.createQuery("from Attractiontypes order by id", AttractionTypes.class);
		List<AttractionTypes> list = query.list();
		return list;
	}

	public AttractionTypes update(int id, String name) {
		AttractionTypes result = session.get(AttractionTypes.class, id);
		if (result != null) {
			result.setName(name);
		}
		return result;
	}

	public boolean delete(int id) {
		AttractionTypes bean = session.get(AttractionTypes.class, id);
		if (bean != null) {
			session.delete(bean);
			return true;
		}
		return false;
	}

}
