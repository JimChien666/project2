package team6.nn.service;

import java.util.List;

import team6.nn.entity.Attractions;

public interface IAttractionsService {

	Attractions insert(Attractions bean);

	Attractions select(int id);

	List<Attractions> selectAll();

	Attractions update(int id, String name);

	boolean delete(int id);

}
