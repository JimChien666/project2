package com.iii.eeit124.shopping.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.shopping.service.CreateOrderService;

import com.iii.eeit124.entity.Orders;


@Controller
public class TestECPayApi {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	CreateOrderService service;
	
	@PostMapping("/TestApi")
	public void goProductListPage(@RequestParam Map<String, String> result, Model model) {
		
		String RtnCode = result.get("RtnCode");
		if ("1".equals(RtnCode)) {
			String MerchantTradeNo = result.get("MerchantTradeNo");
			service.updateOrderIsPaid(MerchantTradeNo);
			
		} else {
			String MerchantTradeNo = result.get("MerchantTradeNo");
			service.updateOrderStatus(MerchantTradeNo, "交易失敗");
			Orders order = (Orders) session.getAttribute("order");
			order.setIsPaid(2);
			order.setStatus("交易失敗");
			session.setAttribute("order", order);
		}
		
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
