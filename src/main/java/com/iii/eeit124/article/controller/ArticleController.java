package com.iii.eeit124.article.controller;

import java.util.Date;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iii.eeit124.article.service.ArticleService;
import com.iii.eeit124.article.service.ForumsService;
import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.Forums;



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
	public String saveArticle(Model model) {
//		Article article = new Article();
		Forums forums = new Forums();
		model.addAttribute(forums);
//		model.addAttribute("allArticleTypes", articleService.getAllArticleTypes());
		return "article/SaveArticle";
	}  
	// save article to db and return the articleList page
	
	/*
	@PostMapping(value = "/saveToDB")
	public String saveToDB(Forums forums, BindingResult result, ModelMap model) {
		
		Article article = forums.getArticle();
		article.setActivitysid(1);
		article.setShowarticle(1);
//		article.setMemberid(1);
		
		java.util.Date now = new java.util.Date();
		Date date = new Date(now.getTime());		
		forums.setCreatedat(date);		
//		forums.setMemberid(1);
		forums.setVoteid(1);
		
		
		forums.setArticle(article);		
//		article.setForums(forums);
		article.getForums().add(forums);
		
		forumsService.save(forums);
		return "redirect:/articleList";
//		if (result.hasErrors()) {
//		return "article/SaveArticle";
//		}
//		articleService.saveFullArticle(entity);
//		return "redirect:/articleList"; //未來改為發表之文章頁面
	}  
	*/
	@PostMapping(value = "/saveToDB")
	public String saveToDB(@ModelAttribute(name = "forums")Forums forums, BindingResult result, ModelMap model) {
//		public String saveToDB(@ModelAttribute(name = "article")Article article, BindingResult result, ModelMap model) {
//		Article article = forums.getArticle();
//		Date date = new Date();
		forums.setCreatedat(new Date());
		Article article = forums.getArticle();
		forums.setArticle(article);
		article.getForums().add(forums);

		
//		System.out.println("....................");
//		System.out.println(forums.toString());
//		System.out.println("....................");
//		forumsService.save(forums);
		forumsService.saveArticle(article);
		return "redirect:/articleList";
		
		
		
	}

	
	//show one article
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
