package com.iii.eeit124.article.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iii.eeit124.article.service.ArticleService;
import com.iii.eeit124.article.service.ForumsService;
import com.iii.eeit124.entity.Article;

import oracle.net.aso.m;

@Controller
public class ArticleController {

	@Autowired
	ArticleService articleService;
	@Autowired
	ForumsService forumsService;
	
	@GetMapping(value = "articleList")
	public String list(Locale locale, Model model, @RequestParam(value = "articletypesId", required = false, defaultValue = "1")Integer id) {
		model.addAttribute("allArticleTypes", articleService.getAllArticleTypes());
		model.addAttribute("Articles", articleService.getAllArticles(id));		
		return "article/ShowAllArticle";
	}

	@GetMapping(value = "saveArticle")
	public String saveArticle() {
//		if (result.hasErrors()) {
//
//			model.addAttribute("articleList", articleService.getAllArticles(0));
			return "article/SaveArticle";
//		}
//		articleService.saveFullArticle(entity);
//		return "redirect:/articleList"; //未來改為發表之文章頁面
	}  
	
	@PostMapping(value = "/saveToDB")
	public String saveToDB(@ModelAttribute("article") Article entity, BindingResult result, ModelMap model) {
		model.addAttribute(attributeValue) //TODO 增加Attribute
		if (result.hasErrors()) {
		return "article/SaveArticle";
		}
		articleService.saveFullArticle(entity);
		return "redirect:/articleList"; //未來改為發表之文章頁面
	}  
	
	
	
	@GetMapping(value = "article")
	public String article(Locale locale, Model model, @RequestParam(value = "articleId")Integer id) {
		model.addAttribute("articleId", articleService.select(id));
		model.addAttribute("forums", forumsService.select(id));
//		if (condition) {
//			
//		}		
		return "article/Article";		
	}
	
}
