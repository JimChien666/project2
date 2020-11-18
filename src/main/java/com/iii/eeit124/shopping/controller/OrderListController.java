package com.iii.eeit124.shopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iii.eeit124.entity.Members;

@Controller
@RequestMapping("/order")
public class OrderListController {
	@Autowired
	HttpSession session;
	
	@GetMapping("/OrderList")
	public String goToOrderListPage(Model model) {
		Members loginOK = (Members)session.getAttribute("LoginOK");
		System.out.println(loginOK);
		model.addAttribute("orderList", loginOK.getOrders());
		return "orders/OrderList";
	}
}
