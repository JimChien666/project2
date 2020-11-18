package com.iii.eeit124.shopping.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iii.eeit124.entity.Members;
import com.iii.eeit124.entity.Orders;
import com.iii.eeit124.shopping.service.OrderListService;

@Controller
@RequestMapping("/order")
public class OrderListController {
	@Autowired
	HttpSession session;
	
	@Autowired
	OrderListService service;
	
	@GetMapping("/OrderList")
	public String goToOrderListPage(Model model) {
		Members loginOK = (Members)session.getAttribute("LoginOK");
		System.out.println(loginOK);
		List<Orders> orderList = service.findAllOrdersByMemberId(loginOK.getId());
		model.addAttribute("orderList", orderList);
		return "orders/OrderList";
	}
}
