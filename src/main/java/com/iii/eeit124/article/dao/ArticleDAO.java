package com.iii.eeit124.article.dao;

import java.util.List;

import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.ArticleTypes;
import com.iii.eeit124.entity.Forums;

public interface ArticleDAO {
	void save(Article entity);

	void update(Article entity);

	void delete(Article entity);

	List<ArticleTypes> getAllArticleTypes();

	List<Article> getAllArticles(int articletypesId);
	
	List<Article> select(Integer pageNo ,Integer recordsPerPage, Integer id);

	Long getRecordCounts(Integer id);
	
	List<Article> serchArticles(Integer pageNo ,Integer recordsPerPage, String serch);
	
	Long getAllRecordCounts();
	
	Article select(int id);
	
	Article saveFullArticle(Article article);
	
	List<Article> personalFollowed(Integer memberid, Integer id);
	
	List<Article> getPersonalPostList(Integer memberid, Integer id);
	
}
