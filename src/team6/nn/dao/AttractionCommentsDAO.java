package team6.nn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import team6.nn.entity.AttractionComments;
import team6.nn.entity.Attractions;

public class AttractionCommentsDAO {
	private Session session;

	public AttractionCommentsDAO(Session session) {
		this.session = session;
	}

	public AttractionComments insert(AttractionComments bean) {
		AttractionComments result = session.get(AttractionComments.class, bean.getId());

		if (result == null) {
			session.save(bean);
			return bean;
		}
		return null;
	}

	public AttractionComments select(int id) {// 查單筆
		return session.get(AttractionComments.class, id);
	}

	public List<AttractionComments> selectAll() {// 查所有,用名子排序
		Query<AttractionComments> query = session.createQuery("from AttractionComments order by id", AttractionComments.class);
		List<AttractionComments> list = query.list();
		return list;
	}

	public AttractionComments update(int id, String comment) {
		AttractionComments result = session.get(AttractionComments.class, id);
		if (result != null) {
			result.setContent(comment);
		}
		return result;
	}

	public boolean delete(int id) {
		AttractionComments bean = session.get(AttractionComments.class, id);
		if (bean != null) {
			session.delete(bean);
			return true;
		}
		return false;
	}

}
