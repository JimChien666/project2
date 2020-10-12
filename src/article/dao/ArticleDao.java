package article.dao;

import java.util.List;

import article.model.AllArticleBean;
import article.model.ArticleBean;

public interface ArticleDao {

	public int saveArticle(ArticleBean ab);
	public int deleteArticle(int no);
	List<AllArticleBean> getAllArticles(int articletypesId);
	
}
