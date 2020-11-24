package com.iii.eeit124.adopt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String processAdoptNotice(
			@RequestParam("animalId") Integer animalsId,
			Model m) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		m.addAttribute("Today", sdf.format(new Date()));
		m.addAttribute("animal", animalsService.read(animalsId));
		return "adopt/AdoptNotice";
	}
	
	@GetMapping("/adoptApply")
	public String processAdoptApply() {
		return "adopt/AdoptApply";
	}
}
