package nn.service;

import java.util.List;

import nn.entity.AttractionComments;


public interface IAttractionCommentsService {

	AttractionComments insert(AttractionComments bean);

	AttractionComments select(int id);

	List<AttractionComments> selectAll();

	AttractionComments update(int id, String name);

	boolean delete(int id);

}
