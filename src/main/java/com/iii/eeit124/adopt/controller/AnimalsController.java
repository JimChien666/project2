package com.iii.eeit124.adopt.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.entity.Animals;

@Controller
public class AnimalsController {
	@Autowired
	public AnimalsService animalsService;
	
	//讀取全部動物的頁面
	@RequestMapping(path = "/readAnimal", method = RequestMethod.GET)
	public String processReadAnimal(Model m) {
		List<Animals> list = animalsService.readAll();
		m.addAttribute("AnimalsList", list);
		return "adopt/ReadAnimal";
	}
	
	@ModelAttribute("AnimalsList1")
	public Animals formBackingObject() {
		return new Animals();
	}
	
	@RequestMapping(path = "/preCreateAnimal.controller", method = RequestMethod.GET)
	public String processPreCreateAnimal(Model m) {
//		Animals animals = new Animals();
//		m.addAttribute("AnimalsList1", animals);
		return "adopt/CreateAnimal";
	}
	
	@RequestMapping(path = "/CreateAnimal.controller", method = RequestMethod.POST)
	public String processCreateAnimal(@ModelAttribute("AnimalsList1") Animals entity, BindingResult result, Model m) {
		if (result.hasErrors()) {
			m.addAttribute("AnimalsList", animalsService.readAll());
			return "adopt/ReadAnimal";
		}
		entity.setCreatedAt(new Date());
		animalsService.create(entity);
		return "adopt/ReadAnimal";
	}
}
