package nn.service;

import java.util.List;

import nn.entity.Tags;

public interface ITagsService {
	public Tags insert(Tags bean);
	public Tags select(int id);
	public List<Tags> selectAll();
	public Tags update(int id, String name);		
	public boolean delete(int id);	
}
