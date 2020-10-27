package nn.service;

import java.util.List;

import team6.nn.entity.Citys;

public interface ICitysService {
	public Citys insert(Citys bean);
	public Citys select(int id);
	public List<Citys> selectAll();
	public Citys update(int id, String name);		
	public boolean delete(int id);	
}
