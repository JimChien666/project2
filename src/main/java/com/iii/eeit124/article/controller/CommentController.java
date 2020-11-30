package com.iii.eeit124.article.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.iii.eeit124.article.service.ForumsService;

@Controller
public class CommentController {
	@Autowired
	HttpSession session;
	@Autowired
	ForumsService forumsService;
	
	
}
