package com.iii.eeit124.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.activity.dao.ActivityApplyDao;
import com.iii.eeit124.entity.ActivityApply;
import com.iii.eeit124.entity.Activitys;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.enums.ApplyType;

@Service
@Transactional
public class ActivityApplyServiceImpl implements ActivityApplyService {

	@Autowired
	ActivityApplyDao activityApplyDao;

	@Override
	public void save(ActivityApply entity) {
		activityApplyDao.save(entity);
	}

	@Override
	public void update(ActivityApply entity) {
		activityApplyDao.update(entity);
	}

	@Override
	public void delete(ActivityApply entity) {
		activityApplyDao.delete(entity);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		delete(findById(id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<ActivityApply> list() {
		return activityApplyDao.list();
	}

	@Override
	public ActivityApply findById(Integer activitysId, Integer memberId) {
		return activityApplyDao.findById(activitysId, memberId);
	}

	@Override
	public ActivityApply findById(Integer id) {
		return activityApplyDao.findById(id);
	}
	
	@Override
	public ActivityApply createPlanner(Activitys activitys, Members members) {
		ActivityApply entity = new ActivityApply();
		entity.setAmount(activitys.getAmount());
		entity.setApplyDate(activitys.getCreateDate());
		entity.setActivitysId(activitys.getId());
		entity.setApplyType(ApplyType.PLANNER.getCode());
		entity.setMemberId(members.getId());
		save(entity);
		return entity;
	}
	
	public void deleteByActivitysId(Integer activitysId) {
		activityApplyDao.deleteByActivitysId(activitysId);
	}

	@Override
	public boolean findByActivityIdAndMenberId(Integer activityId, Integer memberId) {
		return activityApplyDao.findByActivityIdAndMenberId(activityId, memberId);
		
	}

	@Override
	public List<ActivityApply> getAppliedActivityIds(Integer memberId) {
		return activityApplyDao.getAppliedActivityIds(memberId);
	}

}
