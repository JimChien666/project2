package com.iii.eeit124.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	/*
	 * 	本方法會回傳購物車品項列表給前端
	 * 若productId為零,則直接回傳購物車的列表
	 * 若不為零,加入此productId商品
	 * 回傳購物車的列表
	 * */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/addCart")
	public @ResponseBody List<CartItems>  addCart(@RequestParam(value="productId", required = false)Integer productId, @RequestParam(value="cartNum", required = false)Integer cartNum,Model model) {
		if (session.getAttribute("cartItems")==null) {
			session.setAttribute("cartItems", new ArrayList<CartItems>());
		}
		List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartItems");
		if (productId!=0) {
			CartItems cartItem = service.getCartItemInfo(productId);

			for(CartItems cartItemMember:cartItems) {
				if(cartItemMember.getProductId().equals(productId)) {
					cartItemMember.setQuantity(cartItemMember.getQuantity() + cartNum);
					return cartItems;
				}
			}
			cartItem.setQuantity(cartNum);
			cartItems.add(cartItem);
		} else {
			
		}
		
		return cartItems;
	}
}
