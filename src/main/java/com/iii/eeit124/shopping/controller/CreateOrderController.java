package com.iii.eeit124.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/order")
public class CreateOrderController {
	@GetMapping("/CreateOrder")
	public String getCreateOrderPage() {
		return "orders/CreateOrder";
	}
	
	@PostMapping("/CreateOrder.controller")
	public boolean createOrder() {
		return true;
	}
}
