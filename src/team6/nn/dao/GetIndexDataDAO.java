package team6.nn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import team6.nn.entity.AttractionTypes;
import team6.nn.entity.Citys;
import team6.nn.entity.Tags;

public class GetIndexDataDAO {
	private Session session;
	public GetIndexDataDAO(Session session) {
		this.session=session;
	}
	
	
	public List<AttractionTypes> selectAllAttractionTypes() {//查所有,用名子排序
		Query<AttractionTypes> query = session.createQuery("from AttractionTypes order by id", AttractionTypes.class);
		List<AttractionTypes> list = query.list();
		return list;
	}
	
}
