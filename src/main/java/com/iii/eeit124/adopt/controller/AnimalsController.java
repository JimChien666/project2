package com.iii.eeit124.adopt.controller;

import java.util.Date;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}
	
//	@ModelAttribute("AnimalsList1") TODO
//	public Animals formBackingObject() {
//		return new Animals();
//	}
	
	//準備一個Animals()進入到CreateAnimal.jsp
	@RequestMapping(path = "/preCreateAnimal.controller", method = RequestMethod.GET)
	public String processPreCreateAnimal(Model m) {
		Animals animals = new Animals();
		m.addAttribute("AnimalsList1", animals);
		return "adopt/CreateAnimal";
	}
	
	//新增完回到ReadAnimal.jsp
	@RequestMapping(path = "/CreateAnimal.controller", method = RequestMethod.POST)
	public String processCreateAnimal(@ModelAttribute("AnimalsList1") Animals entity, BindingResult result, Model m) {
		if (result.hasErrors()) {
			m.addAttribute("AnimalsList", animalsService.readAll());
			return "adopt/ReadAnimal";
		}
		entity.setCreatedAt(new Date());
		animalsService.create(entity);
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}
	
	//新增完回到ReadAnimal.jsp重整頁面
	@RequestMapping(path = "/CreateAnimal.controller", method = RequestMethod.GET)//TODO 可簡化?
	public String processCreateAnimal(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}
	
//	@RequestMapping(path = "/CreateAnimal1.controller", method = RequestMethod.POST)
//	public String processCreateAnimalFile(@ModelAttribute("AnimalsList1") Animals entity, BindingResult result, Model m) {
//		return null;
//	}
	
	//ReadAnimal.jsp跳轉到修改頁
//	@GetMapping("preUpdateAnimal.controller?animalId={animalId}")
	//, value = "preUpdateAnimal.controller?animalId={animalId}"
	@RequestMapping(path = "/preUpdateAnimal.controller", method = RequestMethod.GET)
	public String processPreUpdateAnimal(@PathVariable(value = "animalId") String animalId, BindingResult result, Model m) {
		m.addAttribute("animals", animalsService.read(animalId));
		return "adopt/UpdateAnimal";
	}//做更新先查出一筆值
}
