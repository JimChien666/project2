package com.iii.eeit124.shopping.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iii.eeit124.entity.CartItems;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.entity.OrderItems;
import com.iii.eeit124.entity.Orders;
import com.iii.eeit124.shopping.service.CreateOrderService;


@Controller
@RequestMapping("/order")
public class CreateOrderController {
	@Autowired
	HttpSession session;
	
	@Autowired
	CreateOrderService service;
	
	@GetMapping("/CreateOrder")
	public String getCreateOrderPage(Model m) {
		Orders order = new Orders();
		m.addAttribute("order", order);
		return "orders/CreateOrder";
	}
	
	@PostMapping("/CreateOrder.controller")
	public String createOrder(@ModelAttribute("order") Orders order,Model m) {			
		Date createdAt = new Date();
		
		Map<String, String> errors = new HashMap<String, String>();
		m.addAttribute("errors", errors);
		if("".equals(order.getBuyerName())||order.getBuyerName()==null) {
			errors.put("buyerName", "請填入訂購人名稱");
		}
		if("".equals(order.getBuyerTel())||order.getBuyerTel()==null) {
			errors.put("buyerTel", "請填入訂購人電話");
		}
		if("".equals(order.getBuyerAddress())||order.getBuyerAddress()==null) {
			errors.put("buyerAddress", "請填入訂購人地址");
		}
		if("".equals(order.getBuyerEmail())||order.getBuyerEmail()==null) {
			errors.put("buyerEmail", "請填入訂購人Email");
		}
		if("".equals(order.getRecipientName())||order.getRecipientName()==null) {
			errors.put("recipientName", "請填入收件人名稱");
		}
		if("".equals(order.getRecipientTel())||order.getRecipientTel()==null) {
			errors.put("recipientTel", "請填入收件人電話");
		}
		if("".equals(order.getRecipientAddress())||order.getRecipientAddress()==null) {
			errors.put("recipientAddress", "請填入收件人地址");
		}
		if("".equals(order.getRecipientEmail())||order.getRecipientEmail()==null) {
			errors.put("recipientEmail", "請填入收件人Email");
		}
		if (!errors.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			return "orders/CreateOrder";
		}
		
		try {
			Double total=0.0;
			Set<OrderItems> orderItems = new HashSet<OrderItems>();
			List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartItems");
			/*
			 * 1.計算訂單total 填入orders
			 * 2.創建一個新的orderItems 將cartItem個別填入，再加到Set<OrderItems>中，放入orders裡
			 * */
			for(CartItems cartItem:cartItems) {
				//將購物車資訊塞入訂單品項中
				OrderItems orderItem = new OrderItems();
				orderItem.setDiscount(cartItem.getDiscount());
				Double subTotal=cartItem.getPrice()*cartItem.getDiscount()*cartItem.getQuantity();
				orderItem.setDiscount(cartItem.getDiscount());
				orderItem.setOrder(order);
				orderItem.setPrice(cartItem.getPrice());
				orderItem.setProductId(cartItem.getProductId());
				orderItem.setProductName(cartItem.getProductName());
				orderItem.setQuantity(cartItem.getQuantity());
				orderItem.setSellerId(cartItem.getMemberId());
				orderItem.setStatus("訂單成立");
				
				orderItem.setCreatedAt(createdAt);
				orderItems.add(orderItem);
				//算訂單總金額
				total+=subTotal;
				
			}

			order.setCreatedAt(createdAt);
			order.setOrderItems(orderItems);
			Members buyer = (Members) session.getAttribute("LoginOK");
			order.setBuyer(buyer);
			order.setStatus("訂單成立");
			order.setTotal(total);
			boolean Issuccess = service.saveOrder(order);
			if(!Issuccess) {
				errors.put("createOrderError", "交易失敗");
			}
		}catch (Exception e) {
			errors.put("createOrderError", "交易失敗");
			e.printStackTrace();
		}
		if (!errors.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			return "orders/CreateOrder";
		}
		session.removeAttribute("cartItems");
		m.addAttribute("order", order);
		m.addAttribute("orderItems", order.getOrderItems());
		return "orders/CreateOrderSuccess";
	}
}
