package com.iii.eeit124.activity.service;

import java.util.List;

import com.iii.eeit124.entity.Activitys;
import com.iii.eeit124.entity.Members;

public interface ActivitysService {

	void save(Activitys entity);

	void update(Activitys entity);

	void delete(Activitys entity);

	List<Activitys> list();

	Activitys findById(Integer id);

	void deleteById(Integer id);

	/**
	 * 建立活動與活動主辦人資料
	 * 
	 * @param entity
	 * @param members
	 */
	void createActivitys(Activitys entity, Members members);
}
