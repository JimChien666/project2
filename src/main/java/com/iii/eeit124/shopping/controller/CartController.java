package com.iii.eeit124.shopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iii.eeit124.entity.CartItems;
import com.iii.eeit124.shopping.service.CartService;


@Controller
@RequestMapping("/cart")
@SessionAttributes(names= {"cartItems"})
public class CartController {
	
	@Autowired
	HttpSession session;
	@Autowired
	CartService service;
	@GetMapping(value = "/addCart")
	public void addCart(@RequestParam(value="productId", required = false)Integer productId, @RequestParam(value="cartNum", required = false)Integer cartNum,Model model) {
		System.out.println("fuck");
		System.out.println(productId + "+" + cartNum);
		if (session.getAttribute("cartItems")==null) {
			session.setAttribute("cartItems", new CartItems());
		}
		model.getAttribute("cartItems");
		CartItems cartItem = service.getCartItemInfo(productId);
		session.getAttribute("cartItems");
		
	}
}
