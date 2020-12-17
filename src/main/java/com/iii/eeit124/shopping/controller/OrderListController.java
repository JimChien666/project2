package com.iii.eeit124.shopping.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.member.service.MemberCenterService;
import com.iii.eeit124.shopping.service.OrderListService;
import com.iii.eeit124.shopping.service.ShoppingAanlysisService;


@Controller
@RequestMapping("/order")
public class OrderListController {
	@Autowired
	HttpSession session;
	
	@Autowired
	OrderListService service;
	
	@Autowired
	ShoppingAanlysisService shoppingAanlysisService;
	
	@Autowired
	MemberCenterService memberCenterService;
	
	@GetMapping("/OrderList")
	public String goToOrderPage() {
		return "orders/OrderList";
	}
	
	@GetMapping("/pagingOrders.json")
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
			System.out.println("here");
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
	
	@GetMapping("/orderAnalysis")
	public String getMyAccountPage(Model model) {
		Members member = (Members)session.getAttribute("LoginOK");
		Map<String, BigDecimal> dataPerMonth =  memberCenterService.getCostHistory(member.getId());
		model.addAttribute("dataPerMonth", dataPerMonth);
		Map<String, BigDecimal> categoriesCost = shoppingAanlysisService.getAllCategoriesCost(member.getId());
		Map<String, BigDecimal> animalTypesCost = shoppingAanlysisService.getAllAnimalTypeCost(member.getId());
		Map<String, BigDecimal> colorsCost = shoppingAanlysisService.getAllColorCost(member.getId());
		model.addAttribute("categoriesCost", categoriesCost);
		model.addAttribute("animalTypesCost", animalTypesCost);
		model.addAttribute("colorsCost", colorsCost);
		return "orders/orderAnalysis";
	}
	
	@GetMapping("/sellingData.json")
	public @ResponseBody Map<String, Object> getSellingData(
			@RequestParam(value="date", required = false) String date
			) throws ParseException{
		Map<String, Object> sellingCount = new HashMap<>();
		Members member = (Members)session.getAttribute("LoginOK");
		if (date == null) {
			sellingCount =  memberCenterService.getSellingHistory(member.getId());			
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date start = sdf.parse(date+"-01");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(start);
			calendar.set(Calendar.DAY_OF_MONTH,1);
			calendar.add(Calendar.MONTH, 1);
			Date last = calendar.getTime();
			System.out.println(last);
			sellingCount =  memberCenterService.getSellingHistory(member.getId(), start, last);
		}
		return sellingCount;
	}
	
	@GetMapping("/sellingCountByDate.json")
	public @ResponseBody Map<String, List<Object>> getSellingCountByDate(
			@RequestParam(value="date", required = false) String date
			) throws ParseException{
		Map<String, List<Object>> sellingCount = new HashMap<>();
		Members member = (Members)session.getAttribute("LoginOK");
		if (date == null) {
			sellingCount =  memberCenterService.getSellingCountByDate(member.getId());
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date start = sdf.parse(date+"-01");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(start);
			calendar.set(Calendar.DAY_OF_MONTH,1);
			calendar.add(Calendar.MONTH, 1);
			Date last = calendar.getTime();
			System.out.println(last);
			sellingCount =  memberCenterService.getSellingCountByDate(member.getId(), start, last);
		}
		return sellingCount;
	}
}
