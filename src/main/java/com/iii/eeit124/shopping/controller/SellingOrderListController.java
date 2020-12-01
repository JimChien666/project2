package com.iii.eeit124.shopping.controller;

import java.util.ArrayList;
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
import com.iii.eeit124.member.service.MemberCenterService;
import com.iii.eeit124.shopping.service.SellingOrderListService;

@Controller
@RequestMapping("/order")
public class SellingOrderListController {
	@Autowired
	HttpSession session;
	
	@Autowired
	SellingOrderListService service;
	
	
	@Autowired
	MemberCenterService memberCenterService;

	@GetMapping("/SellingOrderList")
	public String goToOrderPage() {
		return "orders/SellingOrderList";
	}
	
	@GetMapping("/pagingSellingOrders.json")
	public @ResponseBody Map<String, Object> getPageOrders(Model model,
			@RequestParam(value="pageNo",defaultValue = "1", required = false) Integer pageNo,
			@RequestParam(value="paidStatus",required = false) Integer paidStatus,
			@RequestParam(value="orderStatus", defaultValue = "", required = false) String orderStatus
			) {
		Map<String, Object> map = new HashMap<>();
		Integer recordsPerPage = 4;
		Members loginOK = (Members)session.getAttribute("LoginOK");
		List<Orders> orderList = new ArrayList<Orders>();
		Long recordCounts= (long) 0;
		if ("".equals(orderStatus)) {
			orderStatus = null;
		}
		if ((paidStatus != null)||(orderStatus != null)) {
			
			orderList = service.findAllOrdersByMemberId(pageNo, recordsPerPage, loginOK.getId(), paidStatus, orderStatus);
			recordCounts = service.getRecordCounts(loginOK.getId(), paidStatus, orderStatus);
		}
		else {
			
			orderList = service.findAllOrdersByMemberId(pageNo, recordsPerPage, loginOK.getId());
			recordCounts = service.getRecordCounts(loginOK.getId());
		}
		Integer totalPage = (int) (Math.ceil(recordCounts / (double) recordsPerPage));
		
		
		map.put("list", orderList);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);
		map.put("recordCounts", recordCounts);
		map.put("recordsPerPage", recordsPerPage);
		return map;
	}
	
	
	@GetMapping("/changeToShipped")
	public @ResponseBody Integer changeToShipped(@RequestParam(value="orderItemId") Integer orderItemId) {
		Integer checkOrderIsUpdate =  service.updateOrderStatus(orderItemId);
		return checkOrderIsUpdate;
	}
}
