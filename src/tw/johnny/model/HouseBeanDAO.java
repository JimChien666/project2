package tw.johnny.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class HouseBeanDAO {
	
	private Session session;
	
	public HouseBeanDAO(Session session) {
		this.session = session;
	}
	
	public HouseBean insert(HouseBean bean) {
		HouseBean result = session.get(HouseBean.class, bean.getHouseid());
		
		if(result==null) {
			session.save(bean);
			return bean;
		}
		return null;
	}
	
	public HouseBean select(int houseId) {
		return session.get(HouseBean.class, houseId);
	}
	
	public List<HouseBean> selectAll(){
		Query<HouseBean> query = session.createQuery("From HouseBean", HouseBean.class);
		List<HouseBean> list = query.list();
		return list;
	}
	
	public HouseBean update(int houseId, String houseName) { //更新
		HouseBean result = session.get(HouseBean.class, houseId);
		
		if (result!=null) {
			result.setHousename(houseName);
		}
		return result;	
	}
	
	
	
	public boolean delete(int houseId) { //刪除
		HouseBean result = session.get(HouseBean.class, houseId);
		
		if(result!=null) {
			session.delete(result);
			return true;
		}
		return false;
	}
	
	
	
}
