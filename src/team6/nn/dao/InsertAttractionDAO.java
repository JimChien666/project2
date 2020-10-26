package team6.nn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import team6.nn.entity.AttractionTypes;
import team6.nn.entity.Citys;
import team6.nn.entity.Tags;

public class InsertAttractionDAO {
	private Session session;
	public InsertAttractionDAO(Session session) {
		this.session=session;
	}
	
	public List<Citys> selectAllCitys() {//查所有,用名子排序
		Query<Citys> query = session.createQuery("from Citys order by id", Citys.class);
		List<Citys> list = query.list();
		return list;
	}
	
	public List<AttractionTypes> selectAllAttractionTypes() {//查所有,用名子排序
		Query<AttractionTypes> query = session.createQuery("from AttractionTypes order by id", AttractionTypes.class);
		List<AttractionTypes> list = query.list();
		return list;
	}
	
	public Citys selectCity(int id) {//查單筆
		return session.get(Citys.class, id);
	}
	
	public AttractionTypes selectAttractionType(int id) {//查單筆
		return session.get(AttractionTypes.class, id);
	}
	
	public Tags selectTag(int id) {//查單筆
		return session.get(Tags.class, id);
	}
	
	public List<Tags> selectAllTags() {//查所有,用名子排序
		Query<Tags> query = session.createQuery("from Tags order by id", Tags.class);
		List<Tags> list = query.list();
		return list;
	}
	
}
