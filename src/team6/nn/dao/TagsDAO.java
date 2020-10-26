package team6.nn.dao;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.query.Query;

import team6.nn.entity.Citys;
import team6.nn.entity.Tags;

public class TagsDAO {

	private Session session;

	public TagsDAO(Session session) {
		this.session = session;
	}

	public Tags insert(Tags bean) {
		Tags result = session.get(Tags.class, bean.getId());

		if (result == null) {
			session.save(bean);
			return bean;
		}
		return null;
	}

	public Tags select(int id) {//查單筆
		return session.get(Tags.class, id);
	}

	public List<Tags> selectAll() {//查所有,用名子排序
		Query<Tags> query = session.createQuery("from tags order by id", Tags.class);
		List<Tags> list = query.list();
		return list;
	}

	public Tags update(int id, String name) {
		Tags result = session.get(Tags.class, id);
		if (result != null) {
			result.setName(name);
		}
		return result;
	}

	public boolean delete(int id) {
		Tags bean = session.get(Tags.class, id);
		if (bean != null) {
			session.delete(bean);
			return true;
		}
		return false;
	}

}
