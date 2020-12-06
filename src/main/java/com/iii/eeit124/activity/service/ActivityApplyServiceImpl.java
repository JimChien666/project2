package com.iii.eeit124.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.activity.dao.ActivityApplyDao;
import com.iii.eeit124.entity.ActivityApply;
import com.iii.eeit124.entity.Activitys;

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
	public ActivityApply findById(Integer id) {
		return activityApplyDao.findById(id);
	}

}
