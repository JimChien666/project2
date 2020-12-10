package com.iii.eeit124.activity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iii.eeit124.activity.vo.ActivitysVo;
import com.iii.eeit124.entity.Activitys;

@Service
public class ActivitysVoService {

	@Autowired
	ActivitysService activitysService;

	public List<ActivitysVo> list() {
		List<Activitys> list = activitysService.list();
		return convert(list);
	}

	public List<ActivitysVo> findByMemberId(Integer memberId, String applyType) {
		List<Activitys> list = activitysService.findByMemberId(memberId, applyType);
		return convert(list);
	}

	private List<ActivitysVo> convert(List<Activitys> list) {
		List<ActivitysVo> result = new ArrayList<>();
		if (!CollectionUtils.isEmpty(list)) {
			result = list.stream().map(ActivitysVo::convert).collect(Collectors.toList());
		}

		return result;
	}
}
