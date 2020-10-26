package johnny.model.service;
/*
 * 
 */
import java.util.List;

import johnny.model.bean.Article;

public interface IArticleService {

	public Article insert(Article bean);
	public Article select(int id);
	public List<Article> selectAll();
	public Article update(int id, String title, int activitysid, int articletypesid);
	public boolean delete(int id);
	
}
