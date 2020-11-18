package com.iii.eeit124.adopt.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iii.eeit124.adopt.service.BreedsService;

@Controller
public class TestController {
	@Autowired
	public BreedsService breedsService;
	
	//SelectBreeds
//	@GetMapping(value="/getBreed.controller", produces = "text/html;charset=UTF-8")
//	@GetMapping(value="/getBreed.controller", produces = "text/plain;charset=UTF-8")
//	@GetMapping(value="/getBreed.controller")
//	public @ResponseBody List<String> processGetBreed(@RequestParam("family") String family) {
//		List<String> breed = breedsService.readAllBreeds(family);
//		return breed;//OK，有列出品種
//	}

}
