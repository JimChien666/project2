package com.iii.eeit124.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.entity.Categories;
import com.iii.eeit124.entity.Colors;
import com.iii.eeit124.shopping.service.CreateProductService;



@Controller
@RequestMapping("/product")
public class CreateProductController {
	
	@Autowired
	CreateProductService service;
	
	@GetMapping("/CreateProduct")
	public String goCreateProductPage() {
		return "products/CreateProduct";
	}
	
	@GetMapping("/getColors")
	public @ResponseBody List<Colors> queryAllColors(Model model) {
		List<Colors> colors = service.findAllColors();
		return colors;
	}
	
	@GetMapping("/getCategories")
	public @ResponseBody List<Categories> queryAllCategories(Model model) {
		List<Categories> categories = service.findAllCatrgories();
		return categories;
	}
	
}
