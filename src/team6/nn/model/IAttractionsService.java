package team6.nn.model;

import java.util.List;

public interface IAttractionsService {

	Attractions insert(Attractions bean);

	Attractions select(int id);

	List<Attractions> selectAll();

	Attractions update(int id, String name);

	boolean delete(int id);

}
