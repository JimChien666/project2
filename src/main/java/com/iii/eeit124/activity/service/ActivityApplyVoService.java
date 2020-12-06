package com.iii.eeit124.activity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iii.eeit124.activity.vo.ActivityApplyVo;
import com.iii.eeit124.entity.ActivityApply;

@Service
public class ActivityApplyVoService {

	@Autowired
	ActivityApplyService activityApplyService;

	public List<ActivityApplyVo> list() {
		List<ActivityApply> list = activityApplyService.list();

		List<ActivityApplyVo> result = new ArrayList<>();
		if (!CollectionUtils.isEmpty(list)) {
			result = list.stream().map(ActivityApplyVo::convert).collect(Collectors.toList());
		}

		return result;
	}

}
