package team6.model;

import java.util.List;

public interface IHouseBeanService {
	public HouseBean insert(HouseBean bean);
	public HouseBean select(int houseid);
	public List<HouseBean> selectAll();
	public HouseBean update(int houseid, String housename);		
	public boolean delete(int houseid);	
}
