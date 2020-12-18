package com.iii.eeit124.aop.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.iii.eeit124.entity.CartItems;
import com.iii.eeit124.entity.OrderItems;
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
 
 @Pointcut(value="execution(* com.iii.eeit124.shopping.controller.TestECPayApi.goOrderSuccessPage(..))") 
 public void pointcutOrderSuccess() {}  

 
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
 	
 @Before(value = "pointcutOrderSuccess()")
 public void snedToSeller(JoinPoint joinPoint) throws InterruptedException {
	 Thread.sleep(1000);
	 Set<OrderItems> set = (Set<OrderItems>)session.getAttribute("orderItems");
		Iterator<OrderItems> iterator = set.iterator();
		Integer sellerId = 0;
		if (iterator.hasNext()) {
			OrderItems oi = iterator.next();
			sellerId = oi.getSellerId();
		}
		session.setAttribute("sellingOrderNotation", "<script>window.onload = function() {doSendCreateOrder("+sellerId+")};</script>");
	 }
}