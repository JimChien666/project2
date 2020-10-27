package nn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import nn.entity.AttractionTypes;



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
