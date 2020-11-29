package com.iii.eeit124.shopping.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.entity.Members;
import com.iii.eeit124.shopping.service.ShoppingAanlysisService;

@Controller
public class ShoppingAanlysisController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ShoppingAanlysisService service;
	
	@GetMapping("/getAllCategoriesCost")
	public @ResponseBody Map<String, BigDecimal> getAllCategoriesCost(){
		Members member = (Members)session.getAttribute("LoginOK");
		Map<String, BigDecimal> categoriesCost = service.getAllCategoriesCost(member.getId());
		return categoriesCost;
	}
}
