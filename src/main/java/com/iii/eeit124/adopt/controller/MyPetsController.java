package com.iii.eeit124.adopt.controller;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.iii.eeit124.adopt.service.AnimalsService;

@Controller
public class MyPetsController {
	@Autowired
	ServletContext sc;
	@Autowired
	public AnimalsService animalsService;
	
	//TODO 需加入 只顯示該會員id的寵物
	@GetMapping("/MyPetsRead")
	public String processMyPetsRead(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/MyPetsRead";
	}
}
