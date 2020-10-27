package nn.dao;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import team6.nn.entity.AttractionTypes;
import team6.nn.entity.Attractions;
import team6.nn.entity.Citys;
import team6.nn.entity.Files;
import team6.nn.entity.Tags;

public class GetOneAttractionTypeDataDAO {
	private Session session;
	public GetOneAttractionTypeDataDAO(Session session) {
		this.session=session;
	}
	
	public List<Attractions> selectOneTypeAttraction(int attrId) {//查所有,用名子排序
		Query<Attractions> query = session.createQuery("from Attractions where ATTRACTION_TYPE_ID = ?0", Attractions.class);
		query.setParameter(0, attrId);
		query.setFirstResult(0);
		query.setMaxResults(3);
		List<Attractions> list = query.list();
		return list;
	}
	
	public List<AttractionTypes> selectAllAttractionTypes() {//查所有,用名子排序
		Query<AttractionTypes> query = session.createQuery("from AttractionTypes order by id", AttractionTypes.class);
		List<AttractionTypes> list = query.list();
		return list;
	}
	
	public Blob getFileBlob(int id) {
		Query<Files> query = session.createQuery("from Files where ID = ?0", Files.class);
		query.setParameter(0, id);
		return query.uniqueResult().getFileBlob();
		
	}
	
}
