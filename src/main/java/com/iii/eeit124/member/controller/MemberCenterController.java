package com.iii.eeit124.member.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/myAccount")
	public String getMemberCenterPage() {
		return "members/memberCenter";
	}
	
	
	@GetMapping("/updateMember")
	public String updateMember() {
		return "members/UpdateMember";
	}
	
////列表中按鈕選更新
	@RequestMapping(path = "/myAccount.controller", method = RequestMethod.GET)
	public String processAdminSelectPost(
//			@RequestParam(name = "id") Integer id, 
			Model m) {

//		Members aMember = memberCenterService.get(id);
//		m.addAttribute("id", id);
		m.addAttribute("aMember", (Members)session.getAttribute("LoginOK"));
		
		return "members/updateMember";
	}

	//// 更新好後取到資料庫內資料SET後UPDATE
	@RequestMapping(path = "/UpdateMember.controller", method = RequestMethod.POST)
	public String processUpdatePost(@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name,
			@RequestParam(name = "memberType") String memberType, @RequestParam(name = "sex") String sex,
			@RequestParam(name = "tel") String tel, @RequestParam(name = "address") String address,
			@RequestParam(name = "email") String email, @RequestParam(name = "account") String account) {

		Members post = (Members)session.getAttribute("LoginOK");

		post.setName(name);
		post.setMemberType(memberType);
		post.setSex(sex);
		post.setTel(tel);
		post.setAddress(address);
		post.setEmail(email);
		post.setAccount(account);
		memberCenterService.update(post);
		return "members/memberCenter";
	}

}
