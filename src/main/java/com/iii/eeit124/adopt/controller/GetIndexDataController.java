package com.iii.eeit124.adopt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.entity.Animals;

@Controller
public class GetIndexDataController {
	
	@Autowired
	public AnimalsService animalsService;
	
	@GetMapping("/getAllAnimal.json")
	public @ResponseBody List<Animals> getAllAnimal(){
		
		List<Animals> readAll=animalsService.showIndexAnimals();
		return readAll;
	}
}
