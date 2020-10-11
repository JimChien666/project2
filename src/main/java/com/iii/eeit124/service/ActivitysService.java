package com.iii.eeit124.service;

import java.util.List;

import com.iii.eeit124.entity.Activitys;

public interface ActivitysService {

	void save(Activitys entity);
	
	void update(Activitys entity);
	
	void delete(Activitys entity);
	
	List<Activitys> list();
	
	Activitys findById(Integer id);
}
