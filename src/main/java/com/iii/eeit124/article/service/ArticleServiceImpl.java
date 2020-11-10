package com.iii.eeit124.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.article.dao.ArticleDAO;
import com.iii.eeit124.entity.Article;
import com.iii.eeit124.entity.ArticleTypes;
@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	ArticleDAO articleDAO;

	@Override
	public void save(Article entity) {
		articleDAO.save(entity);
	}

	@Override
	public void update(Article entity) {
		articleDAO.update(entity);
	}

	@Override
	public void delete(Article entity) {
		articleDAO.delete(entity);
	}

	@Override
	public List<ArticleTypes> getAllArticleTypes() {
		return articleDAO.getAllArticleTypes();
	}

	@Override
	public List<Article> getAllArticles(int articletypesId) {
		return articleDAO.getAllArticles(articletypesId);
	}

	@Override
	public Article select(int id) {
		return articleDAO.select(id);
	}

	@Override
	public Article saveFullArticle(Article article) {
		return articleDAO.saveFullArticle(article);
	}

}
