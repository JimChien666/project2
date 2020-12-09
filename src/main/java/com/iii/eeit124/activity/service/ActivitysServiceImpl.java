package com.iii.eeit124.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.activity.dao.ActivitysDao;
import com.iii.eeit124.entity.Activitys;
import com.iii.eeit124.entity.Members;

@Service
public class ActivitysServiceImpl implements ActivitysService {

	@Autowired
	ActivitysDao activitysDao;
	
	@Autowired
	ActivityApplyService activityApplyService;

	@Override
	@Transactional
	public void save(Activitys entity) {
		activitysDao.save(entity);
	}
	
	@Override
	@Transactional
	public void update(Activitys entity) {
		activitysDao.update(entity);
	}
	
	@Override
	@Transactional
	public void delete(Activitys entity) {
		activitysDao.delete(entity);
	}
	
	@Override
	@Transactional
	public void deleteById(Integer id) {
		delete(findById(id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Activitys> list() {
		return activitysDao.list();
	}

	@Override
	public Activitys findById(Integer id) {
		return activitysDao.findById(id);
	}
	
	@Override
	public void createActivitys(Activitys entity, Members members) {
		save(entity);
		activityApplyService.createPlanner(entity, members);
	}

}
