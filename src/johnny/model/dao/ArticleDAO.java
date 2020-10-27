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
	
	public List<Article> getAllArticles(int articletypesId){
		Query<Article> query = session.createQuery("from Article where articletypesid = ?1 order by id desc", Article.class);
		query.setParameter(1, articletypesId);
		List<Article> list = query.list();
		return list;
	}	
	
	public List<ArticleTypes> getAllArticleTypes(){
		Query<ArticleTypes> query = session.createQuery("From ArticleTypes", ArticleTypes.class);
//		Query<ArticleTypes> query = session.createQuery("select id, articletype From ArticleTypes", ArticleTypes.class);
		List<ArticleTypes> list = query.list();
		return list;
	}
	
//	public Article update(int id, String title, int activitysid, int memberid, int showarticle, int articletypesid) {
//		Article result = session.get(Article.class, id);
//		if (result!=null) {
//			result.setTitle(title);
//			result.setActivitysid(activitysid);
//			result.setArticletypesid(articletypesid);
//		}		
//		return result;		
//	}
	
//	public Article update(Article article) {
//		Article result = session.get(Article.class, Article);
//		if (result!=null) {
//			result.setTitle(title);
//			result.setActivitysid(activitysid);
//			result.setArticletypesid(articletypesid);
//		}		
//		return result;		
//	}
	
	public boolean delete(int id) {
		System.out.println("123");
		Article result = session.get(Article.class, id);
		if (result!=null) {
			session.delete(result);
			System.out.println("456");
//			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	
}
