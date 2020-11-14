package com.iii.eeit124.article.dao;

import java.util.List;

import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.Forums;

public interface ForumsDao {
	void save(Forums forums);
	List<Forums> select(int id);
	void saveArticle(Article article);
	void updateArticle(Article article);
	Forums selectForum(int id);
}
