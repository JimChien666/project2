package com.iii.eeit124.adopt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.iii.eeit124.adopt.service.AnimalsService;

@Controller
public class AdoptController {

	@Autowired
	public AnimalsService animalsService;

	@GetMapping("/adopt")
	public String processAdopt(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/AdoptAnimal";
	}
	
	@GetMapping("/adoptNotice")
	public String processAdoptNotice(Model m) {
//		m.addAttribute("Animal", );
		return "adopt/AdoptNotice";
	}
	
	@GetMapping("/adoptApply")
	public String processAdoptApply() {
		return "adopt/AdoptApply";
	}
}
