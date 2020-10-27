package nn.service;

import java.util.List;

import nn.entity.AttractionTypes;


public interface IAttractionTypesService {
	public AttractionTypes insert(AttractionTypes bean);
	public AttractionTypes select(int id);
	public List<AttractionTypes> selectAll();
	public AttractionTypes update(int id, String name);		
	public boolean delete(int id);	
}
