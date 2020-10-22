package team6.nn.model;

import java.util.List;

public interface ICityBeanService {
	public CityBean insert(CityBean bean);
	public CityBean select(int id);
	public List<CityBean> selectAll();
	public CityBean update(int id, String name);		
	public boolean delete(int id);	
}
