package com.iii.eeit124.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/order")
public class CreateOrderController {
	@GetMapping("/CreateOrder")
	public String createOrder() {
		return "orders/CreateOrder";
	}
}
