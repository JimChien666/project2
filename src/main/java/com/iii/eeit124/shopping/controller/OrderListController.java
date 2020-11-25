package com.iii.eeit124.shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String goToOrderPage() {
		return "orders/OrderList";
	}
	
	@GetMapping("/pagingOrders.json")
	public @ResponseBody Map<String, Object> getPageOrders(Model model, @RequestParam(value="pageNo",defaultValue = "1") Integer pageNo) {
		Map<String, Object> map = new HashMap<>();
		Integer recordsPerPage = 4;
		Members loginOK = (Members)session.getAttribute("LoginOK");
//		System.out.println(loginOK);
		List<Orders> orderList = service.findAllOrdersByMemberId(pageNo, recordsPerPage, loginOK.getId());
		Long recordCounts = service.getRecordCounts(loginOK.getId());
		Integer totalPage = (int) (Math.ceil(recordCounts / (double) recordsPerPage));
//		model.addAttribute("orderList", orderList);
		map.put("list", orderList);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);
		map.put("recordCounts", recordCounts);
		map.put("recordsPerPage", recordsPerPage);
		return map;
	}
}
