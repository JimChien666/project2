package johnny.model.service;
/*
 * 
 */
import java.util.List;

import org.hibernate.Session;

import johnny.model.bean.Article;
import johnny.model.dao.ArticleDAO;

public class ArticleService implements IArticleService {

	private ArticleDAO aDao;
	
	public ArticleService(Session session) {
		aDao = new ArticleDAO(session);
	}
	
	@Override
	public Article insert(Article bean) {
		return aDao.insert(bean);
	}

	@Override
	public Article select(int id) {
		return aDao.select(id);
	}

//	@Override
//	public List<Article> selectAll() {
//		return aDao.selectAll();
//	}

	@Override
	public Article update(int id, String title, int activitysid, int articletypesid) {
		return aDao.update(id, title, activitysid, articletypesid);
	}

	@Override
	public boolean delete(int id) {
		return aDao.delete(id);
	}

}
