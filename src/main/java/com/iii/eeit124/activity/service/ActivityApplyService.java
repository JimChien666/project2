package com.iii.eeit124.activity.service;

import java.util.List;

import com.iii.eeit124.entity.ActivityApply;
import com.iii.eeit124.entity.Activitys;
import com.iii.eeit124.entity.Members;

public interface ActivityApplyService {

	void save(ActivityApply entity);

	void update(ActivityApply entity);

	void delete(ActivityApply entity);

	void deleteById(Integer id);

	List<ActivityApply> list();

	ActivityApply findById(Integer activitysId, Integer memberId);

	/**
	 * 建立活動主辦人資料
	 * 
	 * @param activitys
	 * @param members
	 * @return
	 */
	ActivityApply createPlanner(Activitys activitys, Members members);
	
	/**
	 * 刪除全部的活動參與紀錄
	 * @param activitysId
	 */
	void deleteByActivitysId(Integer activitysId);

	boolean findByActivityIdAndMenberId(Integer activityId, Integer id);

	List<ActivityApply> getAppliedActivityIds(Integer id);

	ActivityApply findById(Integer id);
}
