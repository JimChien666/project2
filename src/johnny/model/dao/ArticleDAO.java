package johnny.model.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import johnny.model.bean.Article;
import johnny.model.bean.ArticleTypes;

public class ArticleDAO {

	private Session session;

	public ArticleDAO(Session session) {
		this.session = session;
	}
	
	public Article insert(Article bean) {
		Article result = session.get(Article.class, bean.getId());
		if (result==null) {
			session.save(bean);
		}
		return null;
	}
	
	public Article select(int id) {
		return session.get(Article.class, id);
	}
	
//	public List<Article> selectAll(){
//		Query<Article> query = session.createQuery("Form Article as article order by id desc", Article.class);
//		List<Article> list = query.list();
//		return list;
//	}
	
	public List<Article> getAllArticles(int article){
		Query<Article> query = session.createQuery("select article.id article.title article.member_id Form Article as article where articletypes_id = ? order by id desc", Article.class);
		List<Article> list = query.list();
		return list;
	}	
	
	public List<ArticleTypes> getAllArticleTypes(){
		Query<ArticleTypes> query = session.createQuery("select t.id t.ArticleType Form articleTypes as t", ArticleTypes.class);
		List<ArticleTypes> list = query.list();
		return list;
	}
	
	public Article update(int id, String title, int activitysid, int articletypesid) {
		Article result = session.get(Article.class, id);
		if (result!=null) {
			result.setTitle(title);
			result.setActivitysid(activitysid);
			result.setArticletypesid(articletypesid);
		}		
		return result;		
	}
	
	public boolean delete(int id) {
		Article result = session.get(Article.class, id);
		if (result!=null) {
			session.delete(result);
			return true;
		}
		return false;
	}
	
	
}
