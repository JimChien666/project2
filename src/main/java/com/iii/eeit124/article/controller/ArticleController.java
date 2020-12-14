package com.iii.eeit124.article.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.iii.eeit124.article.service.ArticleService;
import com.iii.eeit124.article.service.CommentsService;
import com.iii.eeit124.article.service.FollowedService;
import com.iii.eeit124.article.service.ForumsService;
import com.iii.eeit124.article.service.MemberOptionService;
import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.Comments;
import com.iii.eeit124.entity.FollowedArticle;
import com.iii.eeit124.entity.Forums;
import com.iii.eeit124.entity.MemberOption;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.entity.Options;


@Controller
@SessionAttributes(names = { "article" })
public class ArticleController {
	@Autowired
	private ServletContext ctx;
	@Autowired
	HttpSession session;
	@Autowired
	ArticleService articleService;
	@Autowired
	ForumsService forumsService;
	@Autowired
	CommentsService commentsService;
	@Autowired
	FollowedService followedService;
	@Autowired
	MemberOptionService memberOptionService;

	@ModelAttribute("article")
	public Article formBackingObject() {
		return new Article();
	}
	// TODO
	@GetMapping("/goArticlePage")
	public String goArticlePage(@RequestParam("articleId") Integer articleId, Model model) {
		model.addAttribute("articleId", articleId);
		model.addAttribute("thisArticle", articleService.select(articleId));
		List<Forums> forumList = forumsService.selectForumById(articleId);
		Forums forumID = forumList.get(0);
		model.addAttribute("forumID", forumID);
		return "article/Article";
	}
	
	
	
//	@GetMapping(value = "testArticle")
//	public String testArticle() {
//		return "article/Article";
//	}

	@GetMapping(value = "/articleList")
	public String list() {
//	public String list(Locale locale, Model model, @RequestParam(value = "articletypesId", required = false, defaultValue = "1") Integer id) {
//		model.addAttribute("allArticleTypes", articleService.getAllArticleTypes());
//		model.addAttribute("Articles", articleService.getAllArticles(id));
		return "article/ShowAllArticle";
	}
//	@GetMapping(value = "articleList")
//	public String list(Locale locale, Model model, @RequestParam(value = "articletypesId", required = false, defaultValue = "1") Integer id) {
//		model.addAttribute("allArticleTypes", articleService.getAllArticleTypes());
//		model.addAttribute("Articles", articleService.getAllArticles(id));
//		return "article/ShowAllArticle";
//	}

	@GetMapping(value = "/backArticle")
	public String backArticle() {
		return "redirect:/articleList";
	}

	@GetMapping(value = "replyArticle/{articleId}")
	public String replyArticle(Model model, @PathVariable(value = "articleId") Integer articleId) {
		System.out.println(articleId);
		Forums forums = new Forums();
		model.addAttribute(forums);
		Article article = articleService.select(articleId);
//		model.addAttribute("forum", forums);
		model.addAttribute("article", article);
		return "article/replyArticle";
	}

	@GetMapping(value = "/saveArticle")
	public String saveArticle(Model model) {
		Forums forums = new Forums();
		model.addAttribute(forums);
		return "article/SaveArticle";
	}
	
	@GetMapping(value = "/updateArticle")
	public String updateArticle(Model model, @RequestParam(value = "articleId") Integer id) {
		Article article = articleService.select(id);
		List<Forums> forumList = forumsService.selectForumById(id);
		Forums forum = forumList.get(0);
		model.addAttribute("article", article);
		model.addAttribute("forum", forum);
		return "article/UpdateArticle";
	}

	@GetMapping(value = "/deleteArticle")
	public String deleteArticle(@RequestParam(value = "articleId", required = false) Integer id) {
		articleService.delete(articleService.select(id));
		System.out.println("...........");
		return "redirect:/articleList";
	}

	@GetMapping(value = "/saveComments", produces = "text/plain;charset=UTF-8")
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
	public String saveToDB(@ModelAttribute(name = "forums") Forums forums, BindingResult result, ModelMap model,
			@RequestParam(value = "voteOption" , required = false) String[] voteOptions,
			@RequestParam(value = "voteImg" , required = false) MultipartFile[] multipartfiles
			) throws SerialException, SQLException {
//		public String saveToDB(@ModelAttribute(name = "article")Article article, BindingResult result, ModelMap model) {
//		Article article = forums.getArticle();
//		Date date = new Date();
		forums.setCreatedat(new Date());
		Article article = forums.getArticle();
		forums.setArticle(article);
		forums.setMember((Members) session.getAttribute("LoginOK"));
		article.getForums().add(forums);
		article.setMember((Members) session.getAttribute("LoginOK"));
		System.out.println("................................................................");
		System.out.println(voteOptions.toString());
		
		List<Options> options = new ArrayList<Options>();
		for(String voteOption:voteOptions) {
			Options option = new Options();
			option.setContent(voteOption);
			option.setForums(forums);
			options.add(option);
		}
		try {
			int index = 0;
			for(MultipartFile file : multipartfiles){
			
				if(file.getSize()>0) {
					
				
					String fileName = file.getOriginalFilename(); //得到原始檔名
					
					String fileTempDirPath = ctx.getRealPath("/") + "UploadTempDir\\"; //創一個臨時資料夾
					File dirPath = new File(fileTempDirPath);
					if(!dirPath.exists()) {
					    boolean status = dirPath.mkdirs();
					    System.out.println("status:" + status);
					}
					String fileSavePath = fileTempDirPath + fileName;
						
				    File saveFile = new File(fileSavePath); 				    
				    file.transferTo(saveFile);
				    
				    HttpHeaders headers = new HttpHeaders();
				    headers.setContentType(MediaType.IMAGE_JPEG);
				    FileInputStream is1 = new FileInputStream(fileSavePath); 
				    byte[] b = new byte[is1.available()];
				    is1.read(b);
				    is1.close();
				    Blob blob = new SerialBlob(b);
				    options.get(index).setOptionBlob(blob);
				    
				    
				}
				index++;
			}
			Set<Options> optionSet = new HashSet<Options>(options);
			forums.setOptions(optionSet);


		}catch (IOException e) {
//			errors.put("errorAccountDup", "新增此筆資料有誤(RegisterServlet)");
			return "redirect:/articleList";
		}
		forumsService.saveArticle(article);
		System.out.println("success");
		return "redirect:/articleList";
	}
	
	@GetMapping(value = "/getVoteResult")
	public @ResponseBody Map<String, Integer> getVoteResult(@RequestParam Integer forumId){
		Map<String, Integer> voteCount = memberOptionService.getVoteCount(forumId);
		return voteCount;		
	}
	
	@GetMapping(value = "/voteConfirm")
	public @ResponseBody boolean voteConfirm(@RequestParam Integer optionid, @RequestParam Integer forumId) {
		MemberOption mOption = new MemberOption();
		mOption.setMembers((Members) session.getAttribute("LoginOK"));
		Options option = memberOptionService.findOptionById(optionid);
		mOption.setOptions(option);
		Forums forums = option.getForums();
		mOption.setForums(forums);		
		boolean checkMemberVoteStatus = memberOptionService.CheckMemberVoteStatus(((Members) session.getAttribute("LoginOK")).getId(), forums.getId());
		System.out.println(checkMemberVoteStatus);
		if (checkMemberVoteStatus) {
			
			memberOptionService.save(mOption);
			return true;
		}
		return false;		
	}
	
	/*
	 * 	update article to DB and return the articleList page.
	 */	 
	@PostMapping(value = "/updateToDB")
	public String updateToDB(@ModelAttribute(name = "article") Article article,
			@RequestParam("content") String forumContent, BindingResult result, Model model) {
//		System.out.println(article.getTitle());
//		System.out.println(article.getFirstForum());
//		article.getFirstForum().setContent(forumContent);
		
		int id = article.getId();
		List<Forums> forumList = forumsService.selectForumById(id);
		Forums forum = forumList.get(0);
		  
		forum.setContent(forumContent);
//		article.addForum(forum);
		Set<Forums> set = new HashSet<Forums>();
		forumList.forEach(element -> set.add(element));
		article.setForums(set);
		
//		article.getForums().add(forum);
		System.out.println(forumContent);
		forumsService.update(article);
//		article.setForums(article.getForums.add(forum));
//		article.setForums(article.getForums().add(forum));
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
	
	
	@GetMapping(value = "/article")
	public @ResponseBody Map<String, Object> getPageArticle(Model model, @RequestParam(value="pageNo",defaultValue = "1") Integer pageNo, @RequestParam(value = "articleId")Integer id){
		Map<String, Object> map = new HashMap<>();
		Integer recordsPerPage = 2;
//		System.out.println("fuck");
//		System.out.println(id);
//		Members loginOK = (Members)session.getAttribute("LoginOK");
		Article article = articleService.select(id);
//		List<Forums> articleList = forumsService.select(pageNo, recordsPerPage, id);
		Long recordCounts = forumsService.getRecordCounts(id);
		List<Forums> forumList = forumsService.select(pageNo, recordsPerPage, id);
		Integer totalPage = (int) (Math.ceil(recordCounts / (double) recordsPerPage));		
		map.put("article", article);
		map.put("forumList", forumList);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);
		map.put("recordCounts", recordCounts);
		map.put("recordsPerPage", recordsPerPage);
		return map;		
	}
	
	@GetMapping(value = "/getArticleList")
	public @ResponseBody Map<String, Object> getArtilceList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(value = "articleTypeId", defaultValue = "1")Integer id){
		Map<String, Object> map = new HashMap<>();
		Integer recordsPerPage = 10;
		System.out.println("id:"+id);
		System.out.println("pageNo:"+pageNo);
		System.out.println("model:"+model);
		Long recordCounts = articleService.getRecordCounts(id);
		List<Article> articleList = articleService.select(pageNo, recordsPerPage, id);
		Members member = (Members) session.getAttribute("LoginOK");
		Integer totalPage = (int) (Math.ceil(recordCounts / (double) recordsPerPage));	
		map.put("articleList", articleList);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);
		map.put("recordCounts", recordCounts);
		map.put("recordsPerPage", recordsPerPage);		
		if (member==null) {
			map.put("statusList", null);			
			return map;
		}
		Integer memberid = member.getId();
		List<FollowedArticle> statusList = followedService.statusCheck(memberid);
		map.put("statusList", statusList);		
		return map;
	}
	
	@GetMapping(value = "/getPersonalArtilceList")
	public @ResponseBody Map<String, Object> getPersonalArtilceList(Model model, @RequestParam(value = "articleTypeId", defaultValue = "1")Integer id){
		Map<String, Object> map = new HashMap<>();

		Members member = (Members) session.getAttribute("LoginOK");
		Integer memberid = member.getId();
		List<Article> articleList = articleService.personalFollowed(memberid, id);
//		List<FollowedArticle> statusList = followedService.personalFollowed(memberid);
		map.put("articleList", articleList);	
		return map;
	}

	@GetMapping(value = "/showComments")
	public @ResponseBody List<Comments> showComments(@RequestParam(value = "forumsId") Integer id) {
		System.out.println("hi:" + id);
		Forums forums = forumsService.selectForum(id);
		List<Comments> comments = commentsService.select(forums.getId());
		System.out.println("hoo :" + comments);
		return comments;		
	}
	
	@GetMapping(value = "/statusChange")
	public @ResponseBody Integer statusChange(Model model, @RequestParam(value = "memberid")Integer memberid, @RequestParam(value = "articleid")Integer articleid) {
		Integer statusResult = followedService.statusChange(memberid, articleid);
		return statusResult;		
	}

	@GetMapping("/member/myArticle")
	public String goMyArticle() {
		return "article/myArticle";
	}
	
}
