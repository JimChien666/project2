package com.iii.eeit124.article.controller;

import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iii.eeit124.article.service.ArticleService;
import com.iii.eeit124.article.service.ForumsService;
import com.iii.eeit124.entity.Activitys;
import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.Forums;



@Controller
@SessionAttributes(names={"article"})
public class ArticleController {

	@Autowired
	ArticleService articleService;
	@Autowired
	ForumsService forumsService;
	
	 @ModelAttribute("article")
	    public Article formBackingObject() {
	        return new Article();
	    }
	
	
	@GetMapping(value = "articleList")
	public String list(Locale locale, Model model, @RequestParam(value = "articletypesId", required = false, defaultValue = "1")Integer id) {
		model.addAttribute("allArticleTypes", articleService.getAllArticleTypes());
		model.addAttribute("Articles", articleService.getAllArticles(id));		
		return "article/ShowAllArticle";
	}

	@GetMapping(value = "saveArticle")
	public String saveArticle(Model model) {
		Forums forums = new Forums();
		model.addAttribute(forums);
		return "article/SaveArticle";
	}  
	
	@GetMapping(value = "updateArticle")
	public String updateArticle(Model model, @RequestParam(value = "articleId")Integer id) {
		Article article = articleService.select(id);		
		model.addAttribute("article", article);
		return "article/UpdateArticle";
	}  
	
	// save article to db and return the articleList page.
	@PostMapping(value = "/saveToDB")
	public String saveToDB(@ModelAttribute(name = "forums")Forums forums, BindingResult result, ModelMap model) {
//		public String saveToDB(@ModelAttribute(name = "article")Article article, BindingResult result, ModelMap model) {
//		Article article = forums.getArticle();
//		Date date = new Date();
		forums.setCreatedat(new Date());
		Article article = forums.getArticle();
		forums.setArticle(article);
		article.getForums().add(forums);		

		forumsService.saveArticle(article);
//		articleService.update(article);
		return "redirect:/articleList";		
	}
	
	
	// update article to db and return the articleList page.
	@PostMapping(value = "/updateToDB")
	public String updateToDB(
			@ModelAttribute(name = "article") Article article,
			@RequestParam("content") String forumContent,
			BindingResult result, 
			Model model) {
		System.out.println(article.getTitle());
		System.out.println(article.getFirstForum());
		article.getFirstForum().setContent(forumContent);
//		System.out.println(article);
		
		System.out.println(forumContent);
		forumsService.update(article);
		return "redirect:/articleList";
//		Article article = forums.getArticle();
//
//		forums.setCreatedat(new Date());
//		forums.setArticle(article);
//		article.getForums().add(forums);		
//
//		forumsService.update(article);
//		return "redirect:/articleList";		
	}

	
	//show one article
	@GetMapping(value = "article")
	public String article(Locale locale, Model model, @RequestParam(value = "articleId")Integer id) {
		Article article = articleService.select(id);
		
//		model.addAttribute("articleId", select);
	
		model.addAttribute("article", article);
		model.addAttribute("forums", article.getForums());
	
		return "article/Article";		
		
	}
	
}
