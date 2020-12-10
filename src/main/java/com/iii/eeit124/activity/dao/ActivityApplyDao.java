package com.iii.eeit124.activity.dao;

import java.util.List;

import com.iii.eeit124.entity.ActivityApply;

public interface ActivityApplyDao {

	void save(ActivityApply entity);
	
	void update(ActivityApply entity);
	
	void delete(ActivityApply entity);
	
	List<ActivityApply> list();
	
	ActivityApply findById(Integer id);
	
	void deleteByActivitysId(Integer activitysId);

	boolean findByActivityIdAndMenberId(Integer activityId, Integer memberId);

	List<ActivityApply> getAppliedActivityIds(Integer memberId);
}
