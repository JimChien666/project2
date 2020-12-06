package com.iii.eeit124.activity.service;

import java.util.List;

import com.iii.eeit124.entity.ActivityApply;

public interface ActivityApplyService {

	void save(ActivityApply entity);

	void update(ActivityApply entity);

	void delete(ActivityApply entity);
	
	void deleteById(Integer id);
	
	List<ActivityApply> list();

	ActivityApply findById(Integer id);
}
