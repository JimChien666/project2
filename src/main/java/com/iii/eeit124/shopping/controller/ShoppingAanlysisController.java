package com.iii.eeit124.shopping.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
	public @ResponseBody List<Map<String, BigDecimal>> getAllCategoriesCost(){
		Members member = (Members)session.getAttribute("LoginOK");
		List<Map<String, BigDecimal>> list = new ArrayList<Map<String, BigDecimal>>();
		Map<String, BigDecimal> categoriesCost = service.getAllCategoriesCost(member.getId());
		Map<String, BigDecimal> animalTypeCost = service.getAllAnimalTypeCost(member.getId());
		Map<String, BigDecimal> colorCost = service.getAllColorCost(member.getId());
		list.add(categoriesCost);
		list.add(animalTypeCost);
		
		return list;
	}
}
