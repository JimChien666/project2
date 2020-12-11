package com.iii.eeit124.activity.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.activity.service.ActivitysVoService;
import com.iii.eeit124.activity.vo.ActivityApplyVo;
import com.iii.eeit124.activity.vo.ActivitysVo;
import com.iii.eeit124.entity.ActivityApply;
import com.iii.eeit124.entity.Members;

@Controller
@RequestMapping("/activitys")
public class myActivityController {
	
	@Autowired
	HttpSession session;
	@Autowired
	ActivitysVoService activitysVoService;
	
	@GetMapping("/myActivity")
	public String gomyActivityPage() {
		return "activitys/myActivity";
	}
	
	@GetMapping("/appliedActivity")
	public String goAppliedActivityPage() {
		return "activityApply/appliedActivity";
	}
	
	@GetMapping("/getMyActivity")
	public @ResponseBody List<ActivitysVo> getMyActivity() {
		Members member = ((Members) session.getAttribute("LoginOK"));
		List<ActivitysVo> list = activitysVoService.findByMemberId(member.getId(), "1");
		return list;
	}
	
	@GetMapping("/getAppliedActivity")
	public @ResponseBody List<ActivitysVo> getAppliedActivity() {
		Members member = ((Members) session.getAttribute("LoginOK"));
		List<ActivitysVo> list = activitysVoService.findByMemberId(member.getId(), "2");
		
		return list;
	}
	
}
