package com.iii.eeit124.util.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
		@GetMapping("/")
		public String homeInit(Locale locale, Model model) {
			return "index";
		}
}

