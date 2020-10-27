package team6.nn.dao;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import team6.nn.entity.AttractionTypes;
import team6.nn.entity.Attractions;
import team6.nn.entity.Citys;
import team6.nn.entity.Files;
import team6.nn.entity.Tags;

public class GetAllAttractionsDataDAO {
	private Session session;
	public GetAllAttractionsDataDAO(Session session) {
		this.session=session;
	}
	
	public List<Attractions> selectOneTypeAttractionsForOnePage(int attrId, int cityId, String searchStr, int page) {//查所有,用名子排序
		System.out.println(searchStr);
		Query<Attractions> query = session.createQuery("from Attractions where ATTRACTION_TYPE_ID = ?0 and CITY_ID = ?1 and NAME like ?2", Attractions.class);
		query.setParameter(0, attrId);
		query.setParameter(1, cityId);
		query.setParameter(2, "%"+searchStr+"%");
		query.setFirstResult((page-1)*9);
		query.setMaxResults(9);
		List<Attractions> list = query.list();
		return list;
	}
	
	public List<Attractions> selectOneTypeAttractions(int attrId, int cityId, String searchStr) {//查所有,用名子排序
		Query<Attractions> query = session.createQuery("from Attractions where ATTRACTION_TYPE_ID = ?0 and CITY_ID = ?1 and NAME like ?2", Attractions.class);
		query.setParameter(0, attrId);
		query.setParameter(1, cityId);
		query.setParameter(2, "%"+searchStr+"%");
		List<Attractions> list = query.list();
		return list;
	}
	
	
	public List<Attractions> selectOneTypeAttractionsForOnePage(int attrId, String searchStr, int page) {//查所有,用名子排序
		Query<Attractions> query = session.createQuery("from Attractions where ATTRACTION_TYPE_ID = ?0 and NAME like ?1", Attractions.class);
		query.setParameter(0, attrId);
		query.setParameter(1, "%"+searchStr+"%");
		query.setFirstResult((page-1)*9);
		query.setMaxResults(9);
		List<Attractions> list = query.list();
		return list;
	}
	
	public List<Attractions> selectOneTypeAttractions(int attrId, String searchStr) {//查所有,用名子排序
		Query<Attractions> query = session.createQuery("from Attractions where ATTRACTION_TYPE_ID = ?0 and NAME like ?1", Attractions.class);
		query.setParameter(0, attrId);
		query.setParameter(1, "%"+searchStr+"%");
		List<Attractions> list = query.list();
		return list;
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
	
}
