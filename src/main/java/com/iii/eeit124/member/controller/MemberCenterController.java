package com.iii.eeit124.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberCenterController {
	
	@GetMapping("/memberCenter")
	public String getMemberCenterPage() {
		return "members/memberCenter";
	}
	
	
}
