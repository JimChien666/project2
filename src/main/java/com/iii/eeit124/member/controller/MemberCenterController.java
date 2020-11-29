package com.iii.eeit124.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.service.MemberCenterService;
import com.iii.eeit124.shopping.service.ShoppingAanlysisService;

@Controller
@RequestMapping("/member")
public class MemberCenterController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ShoppingAanlysisService service;
	
	@Autowired
	MemberCenterService memberCenterService;
	
	@GetMapping("/memberCenter")
	public String getMemberCenterPage() {
		return "members/memberCenter";
	}
	
	@GetMapping("/myAccount")
	public String getMyAccountPage() {
		Members member = (Members)session.getAttribute("LoginOK");
//		Map<String, Double> dataPerMonth =  memberCenterService.getDataPerMonth(member.getId());
		return "members/myAccount";
	}

}
