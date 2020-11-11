package com.iii.eeit124.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.service.ProductListService;

@Controller
@RequestMapping("/product")
public class ProdcutListController {
	@Autowired
	ProductListService service;
	
	@GetMapping("/ProductList")
	public String goProductListPage(Model model) {
		return "products/ProductList";
	}
	@GetMapping("/getProducts")
	public @ResponseBody List<Products> queryAllProducts(Model model){
		List<Products> prodcuts = service.findAllProducts();
		return prodcuts;
	}
}
