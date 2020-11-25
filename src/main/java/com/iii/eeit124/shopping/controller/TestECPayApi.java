package com.iii.eeit124.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class TestECPayApi {
	@PostMapping("/TestApi")
	public void goProductListPage(@RequestBody String result, Model model) {
		System.out.println("fuck");
		System.out.println(result);
	}
	@GetMapping("/goPayPage")
	public @ResponseBody String goPayPage(Model model) {
		String form = (String)model.getAttribute("form");
		return form;
	}
	
	@GetMapping("/goOrderSuccessPage")
	public String goOrderSuccessPage(Model model) {
		return "orders/CreateOrderSuccess";
	}
	
}
