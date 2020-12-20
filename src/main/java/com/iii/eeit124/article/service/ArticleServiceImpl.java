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

	@Override
	public List<Article> select(Integer pageNo, Integer recordsPerPage, Integer id) {
		return articleDAO.select(pageNo, recordsPerPage, id);
	}

	@Override
	public Long getRecordCounts(Integer id) {
		return articleDAO.getRecordCounts(id);
	}

	@Override
	public List<Article> personalFollowed(Integer memberid, Integer id) {
		return articleDAO.personalFollowed(memberid, id);
	}

	@Override
	public List<Article> serchArticles(Integer pageNo, Integer recordsPerPage, String serch) {
		return articleDAO.serchArticles(pageNo, recordsPerPage, serch);
	}

	@Override
	public Long getAllRecordCounts() {
		return articleDAO.getAllRecordCounts();
	}

	@Override
	public List<Article> getPersonalPostList(Integer memberid, Integer id) {
		return articleDAO.getPersonalPostList(memberid, id);
	}

}
