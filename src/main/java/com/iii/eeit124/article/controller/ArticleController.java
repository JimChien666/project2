package com.iii.eeit124.article.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iii.eeit124.article.service.ArticleService;
import com.iii.eeit124.article.service.CommentsService;
import com.iii.eeit124.article.service.ForumsService;
import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.Comments;
import com.iii.eeit124.entity.Forums;
import com.iii.eeit124.entity.Members;

@Controller
@SessionAttributes(names = { "article" })
public class ArticleController {

	@Autowired
	HttpSession session;
	@Autowired
	ArticleService articleService;
	@Autowired
	ForumsService forumsService;
	@Autowired
	CommentsService commentsService;

	@ModelAttribute("article")
	public Article formBackingObject() {
		return new Article();
	}
	
	@GetMapping("goArticlePage")
	public String goArticlePage(@RequestParam("articleId") Integer articleId, Model model) {
		model.addAttribute("articleId", articleId);
		return "article/Article";
	}
	
	
	@GetMapping(value = "testArticle")
	public String testArticle() {
		return "article/Article";
	}

	@GetMapping(value = "articleList")
	public String list(Locale locale, Model model,
			@RequestParam(value = "articletypesId", required = false, defaultValue = "1") Integer id) {
		model.addAttribute("allArticleTypes", articleService.getAllArticleTypes());
		model.addAttribute("Articles", articleService.getAllArticles(id));
		return "article/ShowAllArticle";
	}

	@GetMapping(value = "backArticle")
	public String backArticle() {
		return "redirect:/articleList";
	}

	@GetMapping(value = "replyArticle")
	public String replyArticle(Model model, @RequestParam(value = "articleId") Integer id) {
		System.out.println(id);
		Forums forums = new Forums();
		model.addAttribute(forums);
		Article article = articleService.select(id);
//		model.addAttribute("forum", forums);
		model.addAttribute("article", article);
		return "article/replyArticle";
	}

	@GetMapping(value = "saveArticle")
	public String saveArticle(Model model) {
		Forums forums = new Forums();
		model.addAttribute(forums);
		return "article/SaveArticle";
	}

	@GetMapping(value = "updateArticle")
	public String updateArticle(Model model, @RequestParam(value = "articleId") Integer id) {
		Article article = articleService.select(id);
		model.addAttribute("article", article);
		return "article/UpdateArticle";
	}

	@GetMapping(value = "deleteArticle")
	public String deleteArticle(@RequestParam(value = "articleId", required = false) Integer id) {
		articleService.delete(articleService.select(id));
		System.out.println("...........");
		return "redirect:/articleList";
	}

	@GetMapping(value = "saveComments", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String saveComments(
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "comment")String comment			
			) {
		Comments c = new Comments();
		c.setComment(comment);

		Forums forums = forumsService.selectForum(id);
		c.setMember((Members) session.getAttribute("LoginOK"));
		c.setForums(forums);

		commentsService.save(c);
		return null;
	}

	@PostMapping(value = "/replyToDB")
	public String replyToDB(@ModelAttribute(name = "forums") Forums forums, BindingResult result, ModelMap model,
			@RequestParam(value = "id", required = false) Integer id) {
		Article article = articleService.select(id);
//		int id = forums.getArticle().getId();
		forums.setCreatedat(new Date());
		forums.setMember((Members) session.getAttribute("LoginOK"));
		forums.setArticle(article);
		article.getForums().add(forums);
		forumsService.save(forums);
		return "redirect:/articleList";
	}

	// save article to DB and return the articleList page.
	@PostMapping(value = "/saveToDB")
	public String saveToDB(@ModelAttribute(name = "forums") Forums forums, BindingResult result, ModelMap model) {
//		public String saveToDB(@ModelAttribute(name = "article")Article article, BindingResult result, ModelMap model) {
//		Article article = forums.getArticle();
//		Date date = new Date();
		forums.setCreatedat(new Date());
		Article article = forums.getArticle();
		forums.setArticle(article);
		forums.setMember((Members) session.getAttribute("LoginOK"));
		article.getForums().add(forums);
		article.setMember((Members) session.getAttribute("LoginOK"));
		forumsService.saveArticle(article);
		return "redirect:/articleList";
	}

	// update article to DB and return the articleList page.
	@PostMapping(value = "/updateToDB")
	public String updateToDB(@ModelAttribute(name = "article") Article article,
			@RequestParam("content") String forumContent, BindingResult result, Model model) {
//		System.out.println(article.getTitle());
//		System.out.println(article.getFirstForum());
		article.getFirstForum().setContent(forumContent);
		System.out.println(forumContent);
		forumsService.update(article);
		return "redirect:/articleList";
	}

	// show one article
//	@GetMapping(value = "article")
//	public String article(Locale locale, Model model, @RequestParam(value = "articleId") Integer id) {
//		Article article = articleService.select(id);
//		List<Forums> forums = forumsService.select(id);
//		model.addAttribute("article", article);
//		model.addAttribute("forums", forums);
//		return "article/Article";
//	}
	
	
	@GetMapping(value = "article")
	public @ResponseBody Map<String, Object> getPageArticle(Model model, @RequestParam(value="pageNo",defaultValue = "1") Integer pageNo, @RequestParam(value = "articleId")Integer id){
		Map<String, Object> map = new HashMap<>();
		Integer recordsPerPage = 2;
		System.out.println("fuck");
		System.out.println(id);
//		Members loginOK = (Members)session.getAttribute("LoginOK");
		Article article = articleService.select(id);
//		List<Forums> articleList = forumsService.select(pageNo, recordsPerPage, id);
		Long recordCounts = forumsService.getRecordCounts(id);
		Integer totalPage = (int) (Math.ceil(recordCounts / (double) recordsPerPage));
		map.put("article", article);
		
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);
		map.put("recordCounts", recordCounts);
		map.put("recordsPerPage", recordsPerPage);
		return map;		
	}

	@GetMapping(value = "showComments")
	public @ResponseBody List<Comments> showComments(@RequestParam(value = "forumsId") Integer id) {		
		Forums forums = forumsService.selectForum(id);
		List<Comments> comments = commentsService.select(forums.getId());
		return comments;		
	}
	


	
	
}
