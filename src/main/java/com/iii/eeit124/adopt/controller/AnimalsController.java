package com.iii.eeit124.adopt.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.entity.Animals;

@Controller
public class AnimalsController {
	
	@Autowired
	public AnimalsService animalsService;
	
	//瀏覽全部
	@RequestMapping(path = "/readAnimal", method = RequestMethod.GET)
	public String processReadAnimal(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}
	
//	@ModelAttribute("AnimalsList1") TODO
//	public Animals formBackingObject() {
//		return new Animals();
//	}
	
	//PreCreate
	@RequestMapping(path = "/preCreateAnimal.controller", method = RequestMethod.GET)
	public String processPreCreateAnimal(Model m) {
		Animals animals = new Animals();
		m.addAttribute("AnimalsList1", animals);
		return "adopt/CreateAnimal";
	}
	
	//Create
	@RequestMapping(path = "/CreateAnimal.controller", method = RequestMethod.POST)
	public String processCreateAnimal(@ModelAttribute("AnimalsList1") Animals entity, BindingResult result, Model m) {
		if (result.hasErrors()) {
			m.addAttribute("AnimalsList", animalsService.readAll());
			return "adopt/ReadAnimal";
		}//TODO 要加新增失敗
		entity.setCreatedAt(new Date());
		animalsService.create(entity);
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}
	
	//Refresh
	@RequestMapping(path = "/CreateAnimal.controller", method = RequestMethod.GET)//TODO 可簡化?
	public String processCreateAnimal(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}
	
//	@RequestMapping(path = "/CreateAnimal1.controller", method = RequestMethod.POST)
//	public String processCreateAnimalFile(@ModelAttribute("AnimalsList1") Animals entity, BindingResult result, Model m) {
//		return null;
//	}
	
	//PreUpdate
	@RequestMapping(path = "/preUpdateAnimal.controller", method = RequestMethod.GET)
	public String processPreUpdateAnimal(@RequestParam("animalId") String animalId, Model m) {
		Animals animals = animalsService.read(animalId);
		m.addAttribute("animals", animals);
		m.addAttribute("note", animals.getNote());
		return "adopt/UpdateAnimal";
	}
	
	//Update
	@RequestMapping(path = "/UpdateAnimal.controller", method = RequestMethod.POST)
	public String processUpdateAnimal(@ModelAttribute("animals") Animals entity, BindingResult result, Model m) {
		if (result.hasErrors()) {
			m.addAttribute("AnimalsList", animalsService.readAll());
			return "adopt/ReadAnimal";
		}//TODO 要加更新失敗
		entity.setUpdatedAt(new Date());
		System.out.println(entity);
		animalsService.update(entity);
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}
	
	//Refresh
	@RequestMapping(path = "/UpdateAnimal.controller", method = RequestMethod.GET)//TODO 可簡化?
	public String processUpdateAnimal(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}
	
	//Delete
	@RequestMapping(path = "/DeleteAnimal.controller", method = RequestMethod.POST)
	public String processDeleteAnimal(@RequestParam("animalId") String animalId, Model m) {
		animalsService.delete(animalId);
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}
	
	//Refresh
	@RequestMapping(path = "/DeleteAnimal.controller", method = RequestMethod.GET)//TODO 可簡化?
	public String processDeleteAnimal(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}
}
