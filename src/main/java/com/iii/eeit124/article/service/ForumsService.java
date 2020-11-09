package com.iii.eeit124.article.service;

import java.util.List;

import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.Forums;

public interface ForumsService {
	void save(Forums forums);
	void saveArticle(Article article);
	List<Forums> select(int id);
}
