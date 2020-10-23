package tw.johnny.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

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
	
	public List<Article> selectAll(){
		Query<Article> query = session.createQuery("Form Article", Article.class);
		List<Article> list = query.list();
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
