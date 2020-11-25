package com.iii.eeit124.shopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.entity.Members;
import com.iii.eeit124.entity.OrderItems;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.service.SellingOrderItemService;


@Controller
@RequestMapping("/order")
public class SellingOrderItemController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	SellingOrderItemService service;
	
	@GetMapping("/SellingOrderItems")
	public String goToSellingOrderItemsPage() {
		return "orders/SellingOrderItems";
	}
	
	@GetMapping(value="/pagingSellingOrderItems.json", produces = { "application/json; charset=UTF-8" })
	public @ResponseBody Map<String, Object> getPageSellingOrderItems(
			@RequestParam(value="pageNo", required = false, defaultValue = "1") Integer pageNo
			){
		Map<String, Object> map = new HashMap<>();
		Members loginOK=(Members)session.getAttribute("LoginOK");
		Integer recordsPerPage = 12;
		List<OrderItems> list = new ArrayList<OrderItems>();
		Long recordCounts = service.getRecordCounts(loginOK.getId());
		Integer totalPage = (int) (Math.ceil(recordCounts / (double) recordsPerPage));
		
		list = service.getPageSellingOrderItems(pageNo, recordsPerPage, loginOK.getId());
		map.put("list", list);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);
		map.put("recordCounts", recordCounts);
		map.put("recordsPerPage", recordsPerPage);
		return map;
	}
}
