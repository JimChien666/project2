package team6.nn.model;

import java.util.List;

public interface IAttractionTypesService {
	public AttractionTypes insert(AttractionTypes bean);
	public AttractionTypes select(int id);
	public List<AttractionTypes> selectAll();
	public AttractionTypes update(int id, String name);		
	public boolean delete(int id);	
}
