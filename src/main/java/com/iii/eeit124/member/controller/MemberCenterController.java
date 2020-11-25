package com.iii.eeit124.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.service.MemberCenterService;

@Controller
@RequestMapping("/member")
public class MemberCenterController {
	
	@Autowired
	MemberCenterService memberCenterService;
	
	@GetMapping("/memberCenter")
	public String getMemberCenterPage() {
		return "members/memberCenter";
	}
	
	@GetMapping("/myAccount")
	public String getMyAccountPage() {
		return "members/myAccount";
	}

}
