package com.iii.eeit124.aop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iii.eeit124.entity.CartItems;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.service.CartService;


@Aspect
public class MyControllerAspect {

	@Autowired
	HttpSession session;
	@Autowired
	CartService service;

 @Pointcut(value="execution(* com.iii.eeit124.shopping.controller.CartController.goToCartPage(..))") 
 public void pointcut() {}  
 

 
 @Before(value = "pointcut()")
 public void checkCartStatus(JoinPoint joinPoint) {
	 if (session.getAttribute("cartItems")==null) {
			session.setAttribute("cartItems", new ArrayList<CartItems>());
		}
	 List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartItems");
	 List<CartItems> cartItemsNew = new ArrayList<CartItems>();
	 String errorString = "";
	 session.setAttribute("error", errorString);
		for(CartItems cartItemOld:cartItems) {
			Products product = service.getProduct(cartItemOld.getProductId());

			if (product.getStatus().contentEquals("已下架")) {
				errorString +="商品"+product.getName()+"已下架<br/>";
				continue;
			}
			cartItemsNew.add(cartItemOld);
			
		}
		if (!errorString.equals("")) {
			
			session.setAttribute("error", errorString);
			session.setAttribute("cartItems", cartItemsNew);
		}
 	}
 	
	 
}