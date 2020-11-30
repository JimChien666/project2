package com.iii.eeit124.adopt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.entity.Animals;

@Controller
public class AdoptController {

	@Autowired
	public AnimalsService animalsService;

	@GetMapping("/adopt")
	public String processAdopt(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		m.addAttribute("source", "AdoptAnimal");
		return "adopt/AdoptAnimal";
	}
	
	//瀏覽動物詳細頁
	@GetMapping("/AdoptAnimalDetails.controller/{id}")
	public String processAdoptAnimalDetail(@PathVariable(name = "id") Integer id, 
//			@ModelAttribute("animal") Animals entity, 
			Model m) {
		Animals animals = animalsService.read(id);
		m.addAttribute("source", "AdoptAnimal");
		m.addAttribute("animal", animals);
		return "adopt/ReadAnimalDetails";
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
	public String processAdoptApply(
			@RequestParam("animalId") Integer animalsId,
			Model m
//			@RequestParam("notice1") Integer notice1
//			, @RequestParam("notice2") Integer notice2
//			, @RequestParam("notice3") Integer notice3
//			, @RequestParam("notice4") Integer notice4
//			, @RequestParam("notice5") Integer notice5
//			, @RequestParam("notice6") Integer notice6
//			, @RequestParam("notice7") Integer notice7
//			, @RequestParam("notice8") Integer notice8
//			, @RequestParam("notice9") Integer notice9
//			, @RequestParam("notice10") Integer notice10
			) {
		m.addAttribute("animal", animalsService.read(animalsId));
//		System.out.println(notice1*2^0+notice2*2^1);
		return "adopt/AdoptApply";
	}
}
