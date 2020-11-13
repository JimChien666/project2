package com.iii.eeit124.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iii.eeit124.entity.CartItems;
import com.iii.eeit124.shopping.service.CartService;


@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService service;
	@GetMapping(value = "/addCart")
	public void addCart(@RequestParam(value="productId", required = false)Integer productId, @RequestParam(value="cartNum", required = false)Integer cartNum,Model model) {
		System.out.println("fuck");
		System.out.println(productId + "+" + cartNum);
		service.getCartItemInfo(productId);
		
	}
}
