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
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.service.CartService;


@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	HttpSession session;
	@Autowired
	CartService service;
	/*
	 * 	本方法會回傳購物車品項列表給前端，作法為
	 * 1.先將購物車裡的商品資訊更新為最新
	 * 2.在判斷商品是否已存在在購物車列表中，若有，則直接將商品數量累加
	 * 若沒有 新增購物車品項
	 * 
	 * 回傳購物車的列表
	 * */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/AddCart")
	public @ResponseBody List<CartItems>  addCart(@RequestParam(value="productId", required = false)Integer productId, @RequestParam(value="cartNum", required = false)Integer cartNum,Model model) {
		if (session.getAttribute("cartItems")==null) {
			session.setAttribute("cartItems", new ArrayList<CartItems>());
		}
		List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartItems");
		//更新cartItems裡的商品成最新資訊
//		for(CartItems cartItemOld:cartItems) {
//			CartItems cartItemNew = service.getCartItemInfo(cartItemOld.getProductId());
//			
//			//若新的購物車品項資訊不為空，更新購物車資訊
//			if (cartItemNew != null) {
//				cartItemOld.setProductName(cartItemNew.getProductName());
//				cartItemOld.setDiscount(cartItemNew.getDiscount());
//				cartItemOld.setPrice(cartItemNew.getPrice());
//				cartItemOld.setMemberId(cartItemNew.getMemberId());
//				cartItemOld.setMemberName(cartItemNew.getMemberName());
//			}
//		}
		//這時，購物車列表的商品資訊為最新的了。再去判斷若購物車列表中原本就有此新增商品，則將數量累加上去
		//若沒有 則新增購物車資訊
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
		} 
		return cartItems;
	}
	//進入購物車頁面
	@GetMapping(value = "/CartList")
	public String goToCartPage(Model model) {
		return "products/CartList";
	}
	//修改購物車商品的數量
	@GetMapping("/FixProductQuantity")
	public @ResponseBody List<CartItems> fixProductQuantity(@RequestParam("productId")Integer productId, @RequestParam("count")Integer count,Model model){
		@SuppressWarnings("unchecked")
		List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartItems");
		for(CartItems cartItemMember:cartItems) {
			if(cartItemMember.getProductId().equals(productId)) {
				cartItemMember.setQuantity(count);
				return cartItems;
			}
		}
		return cartItems;
	}
	//刪除其中一項購物車商品
	@GetMapping("/DeleteCartItem")
	public @ResponseBody List<CartItems> deleteCartItem(@RequestParam("productId")Integer productId,Model model) {
		@SuppressWarnings("unchecked")
		List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartItems");
		for(CartItems cartItemMember:cartItems) {
			if(cartItemMember.getProductId().equals(productId)) {
				cartItems.remove(cartItemMember);
				return cartItems;
			}
		}
		return cartItems;
	}
	
	@GetMapping("/ClaerCartItems")
	public @ResponseBody List<CartItems> claerCartItems(Model model) {
		List<CartItems> cartItems = new ArrayList<>();
		session.setAttribute("cartItems", cartItems);
		return cartItems;
	}
	
	@GetMapping("/getProductQuantity")
	public @ResponseBody Integer getProductQuantity(@RequestParam("productId") Integer productId) {
		Products product = service.getProduct(productId);
		return product.getQuantity();
	}
	
}
